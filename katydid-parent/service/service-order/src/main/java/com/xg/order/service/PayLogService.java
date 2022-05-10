package com.xg.order.service;

import com.xg.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-04-11
 */
public interface PayLogService extends IService<PayLog> {

    /**
     * 根据 订单号，创建微信支付二维码接口
     */
    Map<String,Object> createNative(String orderNo);

    /**
     * 查询支付状态
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     * 添加支付记录和更新
     */
    void updateOrderStatus(Map<String, String> map);
}
