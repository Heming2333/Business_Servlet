package com.jzfblog.store.service;

import java.util.List;

import com.jzfblog.store.domain.Category;

public interface CategoryService {

	/**
	 * ���ط����µ�������Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<Category> getAllCats() throws Exception;

	/**
	 * ��ӷ���
	 * @param category ����
	 */
	public void addCategory(Category category) throws Exception;

	/**
	 * ���ص�ǰ������Ϣ
	 * @param cid
	 * @return
	 */
	public Category findCategoryByCid(String cid) throws Exception;

	/**
	 * �޸ķ�����Ϣ
	 * @param category
	 */
	public void updateCategory(Category category) throws Exception;
}
