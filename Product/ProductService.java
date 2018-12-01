package com.jzfblog.store.service;

import java.util.List;

import com.jzfblog.store.domain.PageModel;
import com.jzfblog.store.domain.Product;

public interface ProductService {
	
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
	 * ��ĳ�����µ����в�Ʒ��ҳ��ʾ
	 * @param cid ����cid
	 * @param currentPage ��ǰҳ
	 * @return pageModel
	 * @throws Exception
	 */
	PageModel findProductByCidWithPage(String cid, int currentPage) throws Exception;

	/**
	 * ��������Ʒ��ҳ��ʾ
	 * @param currentPage ��ǰҳ
	 * @return
	 */
	PageModel findAllProductsWithPage(int currentPage) throws Exception;

	/**
	 * ������Ʒ��Ϣ
	 * @param product ��Ʒ
	 */
	void saveProduct(Product product) throws Exception;
}
