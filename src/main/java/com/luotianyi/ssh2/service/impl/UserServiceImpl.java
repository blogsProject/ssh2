package com.luotianyi.ssh2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luotianyi.ssh2.dao.UserDAO;
import com.luotianyi.ssh2.entiy.User;
import com.luotianyi.ssh2.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDAO userDao;

	@Autowired
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public int deleteByUsername(String username) {
		return userDao.deleteByUsername(username);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public int updateByUser(User user) {
		return userDao.updateByUser(user);
	}

	@Override
	public List<User> getAllUser(int first, int count) {
		return userDao.getAllUser(first, count);
	}

	@Override
	public long getAllPage(int divNum) {
		return userDao.getAllPage(divNum);
	}

}
