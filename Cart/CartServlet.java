package com.jzfblog.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzfblog.store.domain.Cart2;
import com.jzfblog.store.domain.CartItem;
import com.jzfblog.store.domain.Product;
import com.jzfblog.store.service.ProductService;
import com.jzfblog.store.service.impl.ProductServiceImpl;
import com.jzfblog.store.web.base.BaseServlet;


public class CartServlet extends BaseServlet {

	// ��ӹ�������ﳵ
	public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		
		// ��session�л�ȡ���ﳵ
		Cart2 cart = (Cart2) request.getSession().getAttribute("cart");
		
		if(null == cart) {
			// �����ȡ�������������ﳵ���󣬷���session��
			cart = new Cart2();
		}
		// ��ȡ��Ʒ��id������
		String pid = request.getParameter("pid");
		int num = Integer.parseInt(request.getParameter("quantity"));
		
		// ͨ����Ʒid��ѯ��Ʒ����
		ProductService ProductService = new ProductServiceImpl();
		try {
			Product product = ProductService.findProductByPid(pid);
			// ��ȡ��������Ĺ�����
			CartItem cartItem = new CartItem();
			cartItem.setNum(num);
			cartItem.setProduct(product);
			
			// ���ù��ﳵ�ϵķ���
			cart.addCartItemToCart(cartItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ע�⣺�˴�ֻ��ʹ���ض��򣬲���ʹ��ת����ת���ᵼ�������ظ��ύ
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// ��ȡ��Ʒpidֵ
		String pid = request.getParameter("pid");
		// ��ȡsession�е�cart
		Cart2 cart = (Cart2) request.getSession().getAttribute("cart");
		cart.removeCartItem(pid);
		response.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Cart2 cart = (Cart2) request.getSession().getAttribute("cart");
		cart.clearCart();
		response.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}

}
