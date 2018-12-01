package com.jzfblog.store.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jzfblog.store.dao.OrderDao;
import com.jzfblog.store.domain.Order;
import com.jzfblog.store.domain.OrderItem;
import com.jzfblog.store.domain.Product;
import com.jzfblog.store.domain.User;
import com.jzfblog.store.utils.JDBCUtils;
import com.mchange.v2.codegen.CodegenUtils;

public class OrderDaoImpl implements OrderDao {

	/**
	 * ���涩��
	 * @throws SQLException 
	 */
	public void saveOrder(Order order) throws SQLException {
		
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Object[] params = {order.getOid(), order.getOrdertime(), order.getTotal(), 
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid()};
		query.update(sql, params);
	}

	/**
	 * ���涩����
	 * @throws SQLException 
	 */
	public void saveOrderItem(OrderItem item) throws SQLException {
		
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Object[] params = {item.getItemid(), item.getQuantity(), 
				item.getTotal(), item.getProduct().getPid(), item.getOrder().getOid()};
		query.update(sql, params);
	}

	/**
	 * ��ȡȫ����¼��
	 */
	public int getTotalRecords(User user) throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from orders where uid=?";
		Long num = (Long) query.query(sql, new ScalarHandler(), user.getUid());
		return num.intValue();
	}

	/**
	 * ��ȡ�û���ҳ����
	 */
	public List findMyOrderWithPage(User user, int startIndex, int pageSize) throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where uid=? limit ?, ?";
		List<Order> list = query.query(sql, new BeanListHandler<Order>(Order.class), user.getUid(), startIndex, pageSize);
		
		// �������ж���
		for (Order order : list) {
			// ��ȡ��ÿ�ʶ���oid����ѯÿ�ʵ��µĶ������Լ��������Ӧ����Ʒ��Ϣ
			
			// ���ڷ��ص������Ƕ������������MapListHandler��װ���ص�����
			String oid = order.getOid();
			sql = "select * from orderItem o, product p where o.pid=p.pid and oid=?";
			List<Map<String , Object>> list02 = query.query(sql, new MapListHandler(), oid);
			
			// ����list
			for (Map<String, Object> map : list02) {
				OrderItem orderItem = new OrderItem();
				Product product = new Product();
				
				// ����BeanUtils���ַ���"1992-3-3"��setBirthday��������������
				// 1.����ʱ�����͵�ת����	
				DateConverter dt = new DateConverter();
				// 2.����ת���ĸ�ʽ
				dt.setPattern("yyyy-MM-dd");
				// 3.ע��ת����
				ConvertUtils.register(dt, java.util.Date.class);
				
				// ��map������orderItem�����Զ���䵽orderItem������
				BeanUtils.populate(orderItem, map);
				// ��map������product�����Զ���䵽product������
				BeanUtils.populate(product, map);
				
				// ��ÿ������������Ʒ������ϵ
				orderItem.setProduct(product);
				// ��ÿ��������Ͷ�����ļ��Ϸ�����ϵ
				order.getList().add(orderItem);
			}
		}
		
		return list;
	}
	
	/**
	 * ���ݶ�����Ų��Ҷ���
	 */
	public Order findOrderByOid(String oid) throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where oid=?";
		
		Order order = query.query(sql, new BeanHandler<Order>(Order.class), oid);
		
		// ���ݶ���id��ѯ���������еĶ������Ӧ����Ʒ��Ϣ
		sql = "select * from orderItem o, product p where o.pid=p.pid and oid=?";
		List<Map<String , Object>> list02 = query.query(sql, new MapListHandler(), oid);
		
		// ����list
		for (Map<String, Object> map : list02) {
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
			
			// ����BeanUtils���ַ���"1992-3-3"��setBirthday��������������
			// 1.����ʱ�����͵�ת����	
			DateConverter dt = new DateConverter();
			// 2.����ת���ĸ�ʽ
			dt.setPattern("yyyy-MM-dd");
			// 3.ע��ת����
			ConvertUtils.register(dt, java.util.Date.class);
			
			// ��map������orderItem�����Զ���䵽orderItem������
			BeanUtils.populate(orderItem, map);
			// ��map������product�����Զ���䵽product������
			BeanUtils.populate(product, map);
			
			// ��ÿ������������Ʒ������ϵ
			orderItem.setProduct(product);
			// ��ÿ��������Ͷ�����ļ��Ϸ�����ϵ
			order.getList().add(orderItem);

		}
		return order;
	}

	/**
	 * ���¶���
	 */
	public void updateOrder(Order order) throws Exception {
		
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update orders set ordertime=?, total=?, state=?, address=?, name=?, telephone=? where oid=?";
		Object[] params = {order.getOrdertime(), order.getTotal(), 
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getOid()};
		query.update(sql, params);
	}

	/**
	 * ��ѯ���ж���
	 */
	public List<Order> findAllOrders() throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders";
		return query.query(sql, new BeanListHandler<Order>(Order.class));
	}

	/**
	 * ��ѯ����״̬�Ķ���
	 */
	public List<Order> findAllOrders(String state) throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where state=?";
		return query.query(sql, new BeanListHandler<Order>(Order.class), state);
	}
	
}
