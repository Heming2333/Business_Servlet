package com.jzfblog.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzfblog.store.domain.PageModel;
import com.jzfblog.store.domain.Product;
import com.jzfblog.store.service.ProductService;
import com.jzfblog.store.service.impl.ProductServiceImpl;
import com.jzfblog.store.web.base.BaseServlet;


public class ProductServlet extends BaseServlet {
	
	/**
	 * ͨ��pid������Ʒ
	 * @param request
	 * @param response
	 * @return /jsp/product_info.jsp
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ��ȡ��Ʒpid
		String pid = request.getParameter("pid");
		// ������Ʒpid��ѯ��Ʒ��Ϣ
		ProductService ProductService = new ProductServiceImpl();
		Product product = null;
		try {
			product = ProductService.findProductByPid(pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����ȡ������Ʒ����request
		request.setAttribute("product", product);
		// ת����/jsp/product_info.jsp
		
		return "/jsp/product_info.jsp";
	}

	/**
	 * ��ҳ��ʾĳ�����µ���Ʒ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findProductByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�����cid����ǰҳcurrentPage
		String cid = request.getParameter("cid");
		int currentPage = Integer.parseInt(request.getParameter("num"));
		// ����ҵ��㹦�ܣ�ʵ�ַ�ҳ��ʽ��ѯ��ǰ������Ʒ��Ϣ
		ProductService ProductService = new ProductServiceImpl();
		PageModel pageModel = null;
		try {
			pageModel = ProductService.findProductByCidWithPage(cid, currentPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��pageBean������õ�request��
		request.setAttribute("page", pageModel);
		// ת����/jsp/product_list.jsp��
		return "/jsp/product_list.jsp";
	}
	
}
