package cn.edu.uts.web.service;

import java.util.List;

import cn.edu.uts.web.base.dao.BaseDao;
import cn.edu.uts.web.domain.Privilege;

public interface PrivilegeService extends BaseDao<Privilege>{

	List<Privilege> findTopList();

	/**
	 * 查询所有权限URl的集合
	 * @return
	 */
	List<String> getAllPrivileges();
}
