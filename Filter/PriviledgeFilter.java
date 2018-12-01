package com.jzfblog.store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jzfblog.store.domain.User;

public class PriviledgeFilter implements Filter {

    public PriviledgeFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest myReq = (HttpServletRequest) request;
		// �жϵ�ǰ��session�Ƿ�����Ѿ���¼�ɹ����û�
		User user = (User) myReq.getSession().getAttribute("loginUser");
		// ������ڣ�����
		if(null != user) {
			chain.doFilter(request, response);
		}else {
			// �������ڣ�ת�뵽��ʾҳ��
			myReq.setAttribute("msg", "���û���¼֮����ȥ����");
			myReq.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
		}
		
		// chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
