package cn.edu.uts.web.service;

import cn.edu.uts.web.base.dao.BaseDao;
import cn.edu.uts.web.domain.User;


public interface UserService extends BaseDao<User> {

	User login(String loginName, String password);

}
