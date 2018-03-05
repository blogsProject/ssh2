package com.luotianyi.ssh2.dao;

import java.util.List;

import com.luotianyi.ssh2.entiy.User;

public interface UserDAO {

	public abstract int insert(User user);

	public abstract int deleteByUsername(String username);

	public abstract User getUserByUsername(String username);

	public abstract int updateByUser(User user);

	public abstract List<User> getAllUser(int first, int count);

	public abstract long getAllPage(int divNum);
}
