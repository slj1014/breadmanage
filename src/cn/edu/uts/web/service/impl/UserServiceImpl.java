package cn.edu.uts.web.service.impl;

import org.springframework.stereotype.Service;

import cn.edu.uts.web.base.dao.impl.BaseDaoImpl;
import cn.edu.uts.web.domain.User;
import cn.edu.uts.web.service.UserService;
import cn.edu.uts.web.util.MD5Util;

@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {
	@Override
	public User login(String loginName, String password) {
		// TODO Auto-generated method stub
		return (User) getSession()
				.createQuery("from User where loginName=? and password=?")
				.setParameter(0, loginName).setParameter(1, MD5Util.GetMD5Code(password))
				.uniqueResult();
	}
}
