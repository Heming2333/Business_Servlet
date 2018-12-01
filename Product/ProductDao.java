package com.jzfblog.store.dao;

import java.util.List;

import com.jzfblog.store.domain.PageModel;
import com.jzfblog.store.domain.Product;

public interface ProductDao {
	
	/**
	 * ��ѯ������Ʒ
	 * @return
	 * @throws Exception
	 */
	List<Product> findHots() throws Exception;
	
	/**
	 * ��ѯ������Ʒ
	 * @return
	 * @throws Exception
	 */
	List<Product> findNews() throws Exception;
	
	/**
	 * ������Ʒ��pid��ѯ��Ʒ
	 * @return
	 * @throws Exception
	 */
	Product findProductByPid(String pid) throws Exception;
	
	/**
	 * ��ѯĳ������ܼ�¼��
	 * @param cid ����cid
	 * @return
	 * @throws Exception
	 */
	int findTotalRecords(String cid) throws Exception;
	
	/**
	 * ��ҳ��ʾĳ����Ĳ�Ʒ
	 * @param cid
	 * @param startIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List findProductByCidWithPage(String cid, int startIndex, int pageSize) throws Exception;

	/**
	 * ��ѯ������Ʒ�ļ�¼��
	 * @return
	 */
	int findAllRecords() throws Exception;

	/**
	 * ��ҳ��ʾ������Ʒ
	 * @param startIndex ��ʼҳ
	 * @param pageSize һҳ��¼��
	 * @return
	 */
	List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception;

	/**
	 * ������Ʒ��Ϣ
	 * @param product ��Ʒ
	 */
	void saveProduct(Product product) throws Exception;
}
