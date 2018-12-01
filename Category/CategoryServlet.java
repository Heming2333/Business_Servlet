package com.jzfblog.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzfblog.store.domain.Category;
import com.jzfblog.store.service.CategoryService;
import com.jzfblog.store.service.impl.CategoryServiceImpl;
import com.jzfblog.store.utils.JedisUtils;
import com.jzfblog.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;


public class CategoryServlet extends BaseServlet {
	
	// ����2��ʹ��ajax���أ�ʡȥÿ��ҳ���servlet��Ҫ��Ӳ�ѯ����
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ��redis�ж���ȫ��������Ϣ���ӿ�����ٶ�
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if(null == jsonStr || "".equals(jsonStr)) {
			// ��ȡȫ��������Ϣ�����ؼ���
			CategoryService CategoryService = new CategoryServiceImpl();
			List<Category> list = null;
			try {
				list = CategoryService.getAllCats();
				// ������ת����json��ʽ����
				jsonStr = JSONArray.fromObject(list).toString();
				jedis.set("allCats", jsonStr);
				// response.setContentType("text/html;charset=utf-8");
				// response.getWriter().write(jsonStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
		JedisUtils.closeJedis(jedis);
		// ת������ʵ����ҳ
		return null;
	}

}
