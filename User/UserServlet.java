package com.jzfblog.store.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzfblog.store.domain.User;
import com.jzfblog.store.service.UserService;
import com.jzfblog.store.service.impl.UserServiceImpl;
import com.jzfblog.store.utils.MailUtils;
import com.jzfblog.store.utils.MyBeanUtils;
import com.jzfblog.store.utils.UUIDUtils;
import com.jzfblog.store.web.base.BaseServlet;


public class UserServlet extends BaseServlet {

	public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}

	public String userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���ձ�����
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		MyBeanUtils.populate(user, map);
		// Ϊ�û����������Ը�ֵ
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		// ����ҵ���ע�Ṧ��
		UserService UserService = new UserServiceImpl();
		try {
			UserService.userRegister(user);
			// ע��ɹ������û����䷢����Ϣ����ת����ʾ����
			// �����ʼ�
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "�û�ע��ɹ����뼤�");
		} catch (Exception e) {
			// ע��ʧ�ܣ���ת����ʾ����
			request.setAttribute("msg", "�û�ע��ʧ�ܣ�������ע�ᣡ");
		}
		return "jsp/info.jsp";
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// ��ȡ������
		String code = request.getParameter("code");
		// ����ҵ���ļ����
		UserService UserService = new UserServiceImpl();
		boolean flag = UserService.userActive(code);
		if(flag == true) {
			// �û�����ɹ�����request������ʾ��Ϣ��ת������¼ҳ��
			request.setAttribute("msg", "�û�����ɹ������¼��");
			return "jsp/login.jsp";
		}else {
			// �û�����ʧ�ܣ���request������ʾ��Ϣ��ת������ʾҳ��
			request.setAttribute("msg", "�û�����ʧ�ܣ������¼��");
			return "jsp/info.jsp";
		}
	}
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�û�����
		User user = new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		// ����ҵ���ĵ�¼����
		UserService UserService = new UserServiceImpl();
		User user02 = null;
		try {
			user02 = UserService.userLogin(user);
			// �û���¼�ɹ������û���Ϣ����session��
			request.getSession().setAttribute("loginUser", user02);
			response.sendRedirect("index.jsp");
			// �ض���ʹ��null
			return null;
		} catch (Exception e) {
			String msg = e.getMessage();
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}
	
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// �������˵�session
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
		return null;
	}
}
