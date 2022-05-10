package com.xg.order.service;

import com.xg.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-04-11
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberIdByJWT);
}
