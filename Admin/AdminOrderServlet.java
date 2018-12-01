package com.jzfblog.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzfblog.store.domain.Order;
import com.jzfblog.store.service.OrderService;
import com.jzfblog.store.service.impl.OrderServiceImpl;
import com.jzfblog.store.web.base.BaseServlet;

public class AdminOrderServlet extends BaseServlet {
	
	public String findOrders(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String state = request.getParameter("state");
		OrderService orderService = new OrderServiceImpl();
		// �����ȡ��stateΪnull����Ϊ��
		if(null==state || "".equals(state)) {
			// ��ȡ��ȫ������
			List<Order> list = orderService.findAllOrders();
			request.setAttribute("allOrders", list);
		}else{
			List<Order> list = orderService.findAllOrders(state);
			request.setAttribute("allOrders", list);
		}
		
		// ת����/admin/order/list.jsp
		return "/admin/order/list.jsp";
	}
	
	public String updateOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// ��ȡ����oid
		String oid = request.getParameter("oid");
		// ��ѯ�ö���
		OrderService orderService = new OrderServiceImpl();
		Order order = orderService.findOrderByOid(oid);
		// ���ö���״̬
		order.setState(3);
		orderService.updateOrder(order);
		// �ض��򵽲�ѯ�ѷ��� ����
		response.sendRedirect("/store/AdminOrderServlet?method=findOrders&state=3");
		//�ض���/admin/AdminOrderServlet
		return null;
	}

}
