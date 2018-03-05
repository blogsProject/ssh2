package com.luotianyi.ssh2.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luotianyi.ssh2.dao.UserDAO;
import com.luotianyi.ssh2.entiy.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCur() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int insert(User user) {
		return (int) getCur().save(user);
	}

	@Override
	public int deleteByUsername(String username) {
		String hql = "delete from User user where user.username = :username";
		Query query = getCur().createQuery(hql);
		query.setParameter("username", username);
		return query.executeUpdate();
	}

	@Override
	public User getUserByUsername(String username) {
		String hql = "from User user where user.username = :username";
		Query query = getCur().createQuery(hql);
		query.setParameter("username", username);
		return (User) query.uniqueResult();
	}

	@Override
	public int updateByUser(User user) {
		String hql = "update User user " + "set user.password = :password,"
				+ "user.fromname = :fromname," + "user.gender = :gender," + "user.email  = :email," + "user.fav = :fav,"
				+ "user.other = :other," + "user.birthday = :birthday " + "where user.username = :username";
		System.out.println(user);
		Query query = getCur().createQuery(hql);
		query.setParameter("username", user.getUsername());
		query.setParameter("password", user.getPassword());
		query.setParameter("fromname", user.getFromname());
		query.setParameter("gender", user.getGender());
		query.setParameter("email", user.getEmail());
		query.setParameter("fav", user.getFav());
		query.setParameter("other", user.getOther());
		query.setParameter("birthday", user.getBirthday());
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser(int first, int count) {
		String hql = "from User user";
		Query query = getCur().createQuery(hql);
		query.setMaxResults(count);
		query.setFirstResult(first * count - count);
		return query.list();
	}

	@Override
	public long getAllPage(int divNum) {
		String hql = "select count(*) from User user";
		Query query = getCur().createQuery(hql);
		return (long) query.uniqueResult();
	}
}
