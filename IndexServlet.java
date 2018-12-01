package com.jzfblog.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzfblog.store.domain.Product;
import com.jzfblog.store.service.ProductService;
import com.jzfblog.store.service.impl.ProductServiceImpl;
import com.jzfblog.store.web.base.BaseServlet;


public class IndexServlet extends BaseServlet {
	
	// ����1��ȱ�㣬ÿ��ҳ��ת������Ҫ�Ӳ�ѯ
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ��ȡȫ��������Ϣ�����ؼ���
		// CategoryService CategoryService = new CategoryServiceImpl();
		// List<Category> list = null;
		// try {
		//	list = CategoryService.getAllCats();
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
		// �����صļ��Ϸ���request
		// request.setAttribute("allCats", list);
		
		// ����ҵ���
		ProductService ProductService = new ProductServiceImpl();
		// ��ѯ���¡�������Ʒ��������������
		List<Product> hots = null;
		List<Product> news = null;
		try {
			hots = ProductService.findHots();
			news = ProductService.findNews();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ���������Ϸŵ���ʵ����ҳ
		request.setAttribute("hots", hots);
		request.setAttribute("news", news);
		
		System.out.println(hots.toString());
		return "/jsp/index.jsp";
	}
	
}
