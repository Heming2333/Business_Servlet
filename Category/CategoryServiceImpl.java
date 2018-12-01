package com.jzfblog.store.service.impl;

import java.util.List;

import com.jzfblog.store.dao.CategoryDao;
import com.jzfblog.store.dao.impl.CategoryDaoImpl;
import com.jzfblog.store.domain.Category;
import com.jzfblog.store.service.CategoryService;
import com.jzfblog.store.utils.BeanFactory;
import com.jzfblog.store.utils.JedisUtils;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService {

	/**
	 * ���ط����µ�������Ϣ
	 */
	public List<Category> getAllCats() throws Exception {
		CategoryDao CategoryDao = (CategoryDao)BeanFactory.createObject("CategoryDao");
		return CategoryDao.getAllCats();
	}

	/**
	 * ��ӷ���
	 */
	public void addCategory(Category category) throws Exception {

		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.addCategory(category);
		// ����redis����
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

	/**
	 * ��ȡ������Ϣ
	 */
	public Category findCategoryByCid(String cid) throws Exception {
		
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.findCategoryByCid(cid);
	}

	/**
	 * ���·�����Ϣ
	 */
	public void updateCategory(Category category) throws Exception {

		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.updateCategory(category);
		
		// ����redis����
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

}
