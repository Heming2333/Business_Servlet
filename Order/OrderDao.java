package com.jzfblog.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.jzfblog.store.domain.Order;
import com.jzfblog.store.domain.OrderItem;
import com.jzfblog.store.domain.User;

public interface OrderDao {

	/**
	 * ���涩��
	 * @param order ����
	 * @throws SQLException
	 */
	void saveOrder(Order order) throws SQLException;

	/**
	 * ���涩����
	 * @param item ������
	 * @throws SQLException
	 */
	void saveOrderItem(OrderItem item) throws SQLException;
	
	/**
	 * ��ȡ�û�ȫ��������
	 * @param user �û�
	 * @return ȫ����¼��
	 * @throws Exception
	 */
	int getTotalRecords(User user) throws Exception;
	
	/**
	 * ��ȡ�û���ҳ����
	 * @param user �û�
	 * @param startIndex ��ʼҳ
	 * @param pageSize һҳ��¼��
	 * @return 
	 * @throws Exception
	 */
	List findMyOrderWithPage(User user, int startIndex, int pageSize) throws Exception;

	/**
	 * ���ݶ���oid���Ҷ���
	 * @param oid �������
	 * @return ����
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
	 * @param state ����״̬
	 * @return
	 */
	List<Order> findAllOrders(String state) throws Exception;
}
