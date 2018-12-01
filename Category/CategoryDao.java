package com.jzfblog.store.dao;

import java.util.List;

import com.jzfblog.store.domain.Category;

public interface CategoryDao {
 
	/**
	 * ���ط�����Ϣ
	 * @return
	 * @throws Exception
	 */
	List<Category> getAllCats() throws Exception;

	/**
	 * ��ӷ���
	 */
	void addCategory(Category category) throws Exception;

	/**
	 * ��ȡ������Ϣ
	 * @param cid ����cid
	 * @return
	 * @throws Exception
	 */
	Category findCategoryByCid(String cid) throws Exception;

	/**
	 * �޸ķ�����Ϣ
	 * @param category
	 */
	void updateCategory(Category category) throws Exception;
}
