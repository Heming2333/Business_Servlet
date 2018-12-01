package com.jzfblog.store.service.impl;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import com.jzfblog.store.dao.UserDao;
import com.jzfblog.store.dao.impl.UserDaoImpl;
import com.jzfblog.store.domain.User;
import com.jzfblog.store.service.UserService;
import com.jzfblog.store.utils.BeanFactory;

public class UserServiceImpl implements UserService{

	/**
	 * ʵ���û�ע�Ṧ��
	 * @throws SQLException 
	 */
	public void userRegister(User user) throws SQLException {
		
		UserDao UserDao = (UserDao)BeanFactory.createObject("UserDao");
		UserDao.userRegister(user);
	}
	
	/**
	 * ʵ���û���ļ���
	 */
	public boolean userActive(String code) throws SQLException {
		
		UserDao UserDao = new UserDaoImpl();
		// ��DB����һ������selcet * from user whrere code = ?
		User user = UserDao.userActive(code);
		if(null != user) {
			// ��ͨ���������ѯ�û�  �޸��û���״̬�����������
			user.setState(1);
			user.setCode(null);
			// �����ݿ�ִ��һ����ʵ�ĸ��²���
			UserDao.updateUser(user);
			return true;
		}else {
			// ������ͨ���������ѯ�û�
			return false;
		}
	}

	/**
	 * ʵ���û���¼����
	 */
	public User userLogin(User user) throws SQLException {
		
		UserDao UserDao = new UserDaoImpl();
		User uu = UserDao.userLogin(user);
		if(null == uu) {
			throw new RuntimeException("��������!");
		}else if(uu.getState() == 0){
			throw new RuntimeException("�û�δ����!");
		}else {
			return uu;
		}
		
	}

}
