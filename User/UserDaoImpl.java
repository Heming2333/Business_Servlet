package com.jzfblog.store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jzfblog.store.dao.UserDao;
import com.jzfblog.store.domain.User;
import com.jzfblog.store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	/**
	 * �û�ע���daoʵ��
	 * @throws SQLException 
	 */
	public void userRegister(User user) throws SQLException {
		String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		// ������ƴ�ճ���������
		Object[] params = {user.getUid(), user.getUsername(), 
				user.getPassword(), user.getName(), 
				user.getEmail(), user.getTelephone(),
				user.getBirthday(), user.getSex(), 
				user.getState(), user.getCode()};
		query.update(sql, params);
	}

	/**
	 * �û���֤��ļ���
	 */
	public User userActive(String code) throws SQLException {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM USER WHERE code = ?";
		User user = query.query(sql, new BeanHandler<User>(User.class), code);
		return user;
	}

	/**
	 * �����û�״̬������û�������
	 */
	public void updateUser(User user) throws SQLException {
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username=?, "
				+ "password=?, name=?, email=?, telephone=?, "
				+ "birthday=?, sex=?, state=?, code=? where uid=?";
		Object[] params = {user.getUsername(), user.getPassword(), 
				user.getName(), user.getEmail(), user.getTelephone(),
				user.getBirthday(), user.getSex(), user.getState(), user.getCode(), user.getUid()};
		query.update(sql, params);
	}

	public User userLogin(User user) throws SQLException {
		
		QueryRunner query = new QueryRunner(JDBCUtils.getDataSource());
		// ��ѯ���ݿ��Ƿ���ڸ��û���������
		String sql = "select * from user where username=? and password=?";
		return query.query(sql, new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
	}
	
}
