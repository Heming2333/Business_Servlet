package com.jzfblog.store.service;

import java.sql.SQLException;

import com.jzfblog.store.domain.User;

public interface UserService {

	/**
	 * ע���û�
	 * @param user �û�
	 */
	public void userRegister(User user) throws SQLException;
	
	/**
	 * �����û���
	 * @param code ������
	 * @return true������ɹ�  false������ʧ��
	 * @throws SQLException
	 */
	public boolean userActive(String code) throws SQLException;
	
	/**
	 * ��¼�û�
	 * @param user �û�
	 * @return �û���Ϣ
	 * @throws SQLException
	 */
	public User userLogin(User user) throws SQLException;
}