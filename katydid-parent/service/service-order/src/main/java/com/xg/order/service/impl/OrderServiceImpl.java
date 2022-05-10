package com.xg.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xg.order.client.EduClient;
import com.xg.order.client.UCenterClient;
import com.xg.order.entity.Order;
import com.xg.order.mapper.OrderMapper;
import com.xg.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.servicebase.exception.KatyException;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-04-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UCenterClient uCenterClient;


    @Override
    public String createOrder(String courseId, String memberIdByJWT) {
        if(StringUtils.isEmpty(memberIdByJWT) || StringUtils.isEmpty(courseId)){
            throw new KatyException(500,"非空");
        }

        //远程调用：根据用户id返回用户信息
        Map<String, Object> courseInfo = eduClient.getCourseInfoToOrder(courseId).getData();

        //远程调用：根据id获取课程信息
        Map<String, Object> memberInfo = uCenterClient.getUserInfoToOrder(memberIdByJWT).getData();

        //创建Order对象，通过set方法设置进去
        Order order = new Order();
        order.setOrderNo(IdWorker.getIdStr());
        order.setStatus(0);     //支付状态：(0未支付)
        order.setPayType(1);    //支付类型：微信1
        order.setCourseId(courseId);
        order.setCourseTitle((String)courseInfo.get("title"));
        order.setCourseCover((String)courseInfo.get("cover"));
        order.setTeacherName((String)courseInfo.get("teacherName"));
        order.setTotalFee(new BigDecimal((String) courseInfo.get("fee")));
        order.setMemberId(memberIdByJWT);
        order.setEmail((String) memberInfo.get("email"));
        order.setNickname((String) memberInfo.get("nickName"));

        baseMapper.insert(order);

        //返回订单号
        return order.getOrderNo();
    }
}
