package com.jzfblog.store.service.impl;

import java.util.List;

import com.jzfblog.store.dao.OrderDao;
import com.jzfblog.store.dao.impl.OrderDaoImpl;
import com.jzfblog.store.domain.Order;
import com.jzfblog.store.domain.OrderItem;
import com.jzfblog.store.domain.PageModel;
import com.jzfblog.store.domain.User;
import com.jzfblog.store.service.OrderService;
import com.jzfblog.store.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService {

	/**
	 * ���涩��
	 */
	public void saveOrder(Order order){
		
		try {
			// ���涩���Ͷ����������еĶ����ͬʱ�ɹ���ʧ�ܣ�
			JDBCUtils.startTransaction();
			OrderDao orderDao = new OrderDaoImpl();
			orderDao.saveOrder(order);
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(item);
			}
			JDBCUtils.commitAndClose();
		} catch (Exception e) {
			JDBCUtils.rollbackAndClose();
		}
	}

	
	public PageModel findMyOrderWithPage(User user, int currentPage) throws Exception {
		
		// ����pageModel��Ŀ�ģ�Я����ҳ����
		OrderDao OrderDao = new OrderDaoImpl();
		int totalRecords = OrderDao.getTotalRecords(user);
		PageModel pageModel = new PageModel(currentPage, totalRecords, 3);
		// ��������
		List list = OrderDao.findMyOrderWithPage(user, pageModel.getStartIndex(), pageModel.getPageSize());;
		pageModel.setRecords(list);
		// ����url
		pageModel.setUrl("OrderServlet?method=findMyOrderWithPage");
		return pageModel;
	}

	/**
	 * ���ݶ�����Ų���󶩵�
	 */
	public Order findOrderByOid(String oid) throws Exception {
		
		// ����dao�㣺���ݶ�����Ų��Ҷ�����Ϣ
		OrderDao orderDao = new OrderDaoImpl();
		Order order = orderDao.findOrderByOid(oid);
		
		return order;
	}

	/**
	 * ���¶���
	 */
	public void updateOrder(Order order) throws Exception {
		
		OrderDao orderDao = new OrderDaoImpl();
		orderDao.updateOrder(order);
	}

	/**
	 * ��ѯ���ж���
	 */
	public List<Order> findAllOrders() throws Exception {
		OrderDao orderDao = new OrderDaoImpl();
		return orderDao.findAllOrders();
	}


	/**
	 * ��ѯ����״̬�Ķ���
	 */
	public List<Order> findAllOrders(String state) throws Exception {
		OrderDao orderDao = new OrderDaoImpl();
		return orderDao.findAllOrders(state);
	}

}
