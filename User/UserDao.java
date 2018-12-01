package com.jzfblog.store.dao;

import java.sql.SQLException;

import com.jzfblog.store.domain.User;

public interface UserDao {

	/**
	 * �û�ע��
	 * @param user
	 */
	public void userRegister(User user) throws SQLException;
	
	/**
	 * �����û���֤��
	 * @param code ������
 	 * @return �ü������û�
	 * @throws SQLException
	 */
	public User userActive(String code) throws SQLException;
	
	/**
	 * �����û�״̬�����������
	 * @param user
	 * @throws SQLException
	 */
	public void updateUser(User user) throws SQLException;
	
	/**
	 * ʵ���û���¼
	 * @param user �û�
	 * @return �û���Ϣ
	 * @throws SQLException
	 */
	public User userLogin(User user) throws SQLException;
}
