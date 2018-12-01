package com.jzfblog.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzfblog.store.domain.Category;
import com.jzfblog.store.service.CategoryService;
import com.jzfblog.store.service.impl.CategoryServiceImpl;
import com.jzfblog.store.utils.UUIDUtils;
import com.jzfblog.store.web.base.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// ��ȡȫ��������Ϣ
		CategoryService CategoryService = new CategoryServiceImpl();
		List<Category> list = CategoryService.getAllCats();
		// ����request
		request.setAttribute("allCats", list);
		// ת����/admin/category/list.jsp
		return "/admin/category/list.jsp";
	}
	
	public String addCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		return "/admin/category/add.jsp";
	}
	
	public String addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// ��ȡ�ͻ����ύ��������
		String cname = request.getParameter("cname");
		// ��������id
		String cid = UUIDUtils.getId();
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		// ����ҵ��㣺��ӷ���
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.addCategory(category);
		// �ض��򵽲�ѯȫ��������Ϣ
		response.sendRedirect("/store/AdminCategoryServlet?method=findAllCats");
		return null;
	}
	
	public String editCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// ��ȡ�ͻ����ύ��cid
		String cid = request.getParameter("cid");
		// ����ҵ��㣺��ȡ������Ϣ
		CategoryService categoryService = new CategoryServiceImpl();
		Category category = categoryService.findCategoryByCid(cid);
		// ��category���뵽request����
		request.setAttribute("category", category);
		System.out.println(category);
		// ת����/admin/category/edit.jsp
		return "/admin/category/edit.jsp";
	}
	
	public String updateCategory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// ��ȡ�ͻ����ύ��cid��cname
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		Category category = new Category(cid, cname);
		// ����ҵ��㣺�޸ķ�����Ϣ
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.updateCategory(category);
		// ת����/admin/category/list.jsp
		return "admin/category/list.jsp";
	}
}
