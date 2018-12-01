package com.jzfblog.store.service;

import java.util.List;

import com.jzfblog.store.domain.Order;
import com.jzfblog.store.domain.PageModel;
import com.jzfblog.store.domain.User;

public interface OrderService {

	/**
	 * ���涩����
	 * @param order ����
	 * @throws Exception
	 */
	void saveOrder(Order order);
	
	/**
	 * ��ѯ�û�����
	 * @param user �û�
	 * @param currentPage ��ǰҳ
	 * @return �����б�
	 * @throws Exception �쳣
	 */
	PageModel findMyOrderWithPage(User user, int currentPage) throws Exception;

	/**
	 * ���ݶ���oid���Ҷ���
	 * @param oid �������
	 * @return
	 */
	Order findOrderByOid(String oid) throws Exception;

	/**
	 * ���¶���
	 * @param order
	 */
	void updateOrder(Order order) throws Exception;

	/**
	 * ��ѯ���ж���
	 * @return �����б�
	 */
	List<Order> findAllOrders() throws Exception;

	/**
	 * ��ѯ����״̬�Ķ���
	 * @param state ״̬
	 * @return
	 */
	List<Order> findAllOrders(String state) throws Exception;
}
