package cn.edu.uts.web.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.uts.web.base.dao.impl.BaseDaoImpl;
import cn.edu.uts.web.domain.Privilege;
import cn.edu.uts.web.service.PrivilegeService;

@Service
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {
	
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}

	@Override
	public List<String> getAllPrivileges() {
		// TODO Auto-generated method stub
		return getSession().createQuery("select distinct p.url from Privilege p where p.url is not null order by id").list();
	}

}
