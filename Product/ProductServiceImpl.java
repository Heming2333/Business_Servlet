package com.jzfblog.store.service.impl;

import java.util.List;

import com.jzfblog.store.dao.ProductDao;
import com.jzfblog.store.dao.impl.ProductDaoImpl;
import com.jzfblog.store.domain.PageModel;
import com.jzfblog.store.domain.Product;
import com.jzfblog.store.service.ProductService;
import com.jzfblog.store.utils.BeanFactory;

public class ProductServiceImpl implements ProductService{

	/**
	 * ��ѯ������Ʒ
	 */
	public List<Product> findHots() throws Exception {
		ProductDao ProductDao = (ProductDao) BeanFactory.createObject("ProductDao");
		return ProductDao.findHots();
	}

	/**
	 * ��ѯ������Ʒ
	 */
	public List<Product> findNews() throws Exception {
		ProductDao ProductDao = new ProductDaoImpl();
		return ProductDao.findNews();
	}

	/**
	 * ����pid��ѯ��Ʒ
	 */
	public Product findProductByPid(String pid) throws Exception {
		ProductDao ProductDao = new ProductDaoImpl();
		return ProductDao.findProductByPid(pid);
	}

	/**
	 * ��ҳ��ʾĳ�����µĲ�Ʒ
	 */
	public PageModel findProductByCidWithPage(String cid, int currentPage) throws Exception {
		ProductDao ProductDao = new ProductDaoImpl();
		// ͳ��ĳ�����Ʒ���ܼ�¼��
		int totalRecords = ProductDao.findTotalRecords(cid);
		PageModel pageModel = new PageModel(currentPage, totalRecords, 12);
		
		// �ó���ҳ�Ĳ�Ʒ����
		List records = ProductDao.findProductByCidWithPage(cid, pageModel.getStartIndex(), pageModel.getPageSize());
		pageModel.setRecords(records);
		pageModel.setUrl("ProductServlet?method=findProductByCidWithPage&cid="+cid);
		return pageModel;
	}

	/**
	 * ��ҳ��ʾ������Ʒ
	 */
	public PageModel findAllProductsWithPage(int currentPage) throws Exception {

		ProductDao productDao = new ProductDaoImpl();
		// ͳ��ȫ����Ʒ�ļ�¼��
		int totalRecords = productDao.findAllRecords();
		// ����PageModel
		PageModel pageModel = new PageModel(currentPage, totalRecords, 10);
		// �ó���ҳ������Ʒ����
		List<Product> list = productDao.findAllProductsWithPage(pageModel.getStartIndex(), pageModel.getPageSize());
		pageModel.setRecords(list);
		pageModel.setUrl("");
		return pageModel;
	}

	/**
	 * ������Ʒ��Ϣ
	 */
	public void saveProduct(Product product) throws Exception {

		ProductDao productDao = new ProductDaoImpl();
		productDao.saveProduct(product);
	}
	
}
