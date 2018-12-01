package com.jzfblog.store.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jzfblog.store.dao.ProductDao;
import com.jzfblog.store.domain.Product;
import com.jzfblog.store.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	/**
	 * ��ѯ������Ʒ
	 */
	public List<Product> findHots() throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pflag=0 and is_hot=0 order by pdate desc limit 0,9";
		return query.query(sql, new BeanListHandler<Product>(Product.class));
	}
	
	/**
	 * ��ѯ������Ʒ
	 */
	public List<Product> findNews() throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pflag=0 order by pdate desc limit 0,9";
		return query.query(sql, new BeanListHandler<Product>(Product.class));
	}

	/**
	 * ����pid��ѯ��Ʒ��Ϣ
	 */
	public Product findProductByPid(String pid) throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pid=?";
		return query.query(sql, new  BeanHandler<Product>(Product.class), pid);
	}

	/**
	 * ��ѯĳ�����ܵļ�¼����
	 */
	public int findTotalRecords(String cid) throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long result = (Long) query.query(sql, new ScalarHandler(), cid);
		return result.intValue();
	}

	/**
	 * ��ҳ��ʾĳ������Ĳ�Ʒ
	 */
	public List findProductByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where cid=? limit ?, ?";
		return query.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
	}

	/**
	 * ��ѯ������Ʒ��¼��
	 */
	public int findAllRecords() throws Exception {
		
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from product";
		
		Long result = (Long) query.query(sql, new ScalarHandler());
		return result.intValue();
	}

	/**
	 * ��ҳ��ʾ������Ʒ
	 */
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product order by pdate desc limit ? offset ?";
		return query.query(sql, new BeanListHandler<Product>(Product.class), pageSize, startIndex);
	}

	/**
	 * ������Ʒ��Ϣ
	 */
	public void saveProduct(Product product) throws Exception {
		
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
					product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid()};
		query.update(sql, params);
	}
	

}
