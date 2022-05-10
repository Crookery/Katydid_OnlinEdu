package com.xg.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.xg.order.entity.Order;
import com.xg.order.entity.PayLog;
import com.xg.order.mapper.PayLogMapper;
import com.xg.order.service.OrderService;
import com.xg.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.order.utils.HttpClient;
import com.xg.servicebase.exception.KatyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-04-11
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;

    @Override
    public Map<String,Object> createNative(String orderNo) {
        try {
            //1.根据订单号查询订单信息
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            Order order = orderService.getOne(wrapper);

            //2.使用map设置生成二维码需要的参数
            Map m = new HashMap();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url","http://guli.shop/api/order/weixinPay/weixinNotify");
            m.put("trade_type", "NATIVE");

            //3.发送httpclient请求，传递参数xml格式
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            httpClient.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            httpClient.setHttps(true);  //支持https
            httpClient.post();

            //4.得到发送请求返回结果
            String content = httpClient.getContent();   //xml格式
            Map<String, String> map = WXPayUtil.xmlToMap(content);
            //重新封装
            Map<String,Object> res = new HashMap<>();
            res.put("out_trade_no", orderNo);
            res.put("course_id", order.getCourseId());
            res.put("total_fee", order.getTotalFee());
            res.put("result_code", map.get("result_code"));     //二维码操作状态
            res.put("code_url", map.get("code_url"));           //二维码地址

            return res;
        } catch (Exception e) {
            throw new KatyException(500,"生成二维码失败");
        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            //2、设置请求
            HttpClient client = new
                    HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            //4、转成Map返回
            return WXPayUtil.xmlToMap(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //获取订单Id
        String orderNo = map.get("out_trade_no");
        //根据订单号查询订单信息
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);

        //更新订单表订单状态
        if(order.getStatus() == 1){
            return;
        }
        order.setStatus(1);
        orderService.update(order,null);

        //添加支付记录
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        payLog.setPayType(1);       //支付类型
        payLog.setTotalFee(order.getTotalFee());    //总金额(分)

        payLog.setTradeState(map.get("trade_state"));       //支付状态
        payLog.setTransactionId(map.get("transaction_id")); //支付流水号

        payLog.setAttr(JSONObject.toJSONString(map));
        //保存
        save(payLog);
    }
}
