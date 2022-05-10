package com.xg.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.commonutils.JwtUtils;
import com.xg.commonutils.Message;
import com.xg.order.entity.Order;
import com.xg.order.service.OrderService;
import com.xg.order.service.PayLogService;
import com.xg.servicebase.exception.KatyException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-04-11
 */

@RestController
@RequestMapping("/order/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayLogService payLogService;

    @ApiOperation("生成订单")
    @PostMapping("createOrder/{courseId}")
    public Message saveOrder(@PathVariable("courseId") String courseId, HttpServletRequest request){
        String orderId = orderService.createOrder(courseId,JwtUtils.getMemberIdByJwtToken(request));
        return Message.successful().add("orderId",orderId);
    }

    @ApiOperation("查询订单信息")
    @GetMapping("getOrderInfo/{orderId}")
    public Message getOrderInfo(@PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return order==null?Message.fail().add("fail_msg","无效id"):Message.successful().add("order",order);
    }

    @ApiOperation("生成微信支付二维码接口")
    @GetMapping("createNative/{orderNo}")
    public Message createNative(@PathVariable String orderNo){
        //返回信息，包含二维码地址
        Map<String,Object> map = payLogService.createNative(orderNo);
        return Message.successful().add("map",map);
    }

    @ApiOperation("查询订单支付状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public Message queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        if(map == null){
            throw new KatyException(500,"支付出问题了");
        }
        if("SUCCESS".equals(map.get("trade_state"))){   //支付成功
            payLogService.updateOrderStatus(map);
            return Message.successful().add("msg","支付成功!");
        }else{
            return Message.successful().add("msg","支付ing").code(2500);
        }
    }

    @ApiOperation("查询用户是否已买课程")
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId,@PathVariable("memberId") String memberId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        //大于0，表示有支付记录
        return count>0;
    }
    
}

