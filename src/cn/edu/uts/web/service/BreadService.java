package cn.edu.uts.web.service;

import java.util.List;

import cn.edu.uts.web.base.dao.BaseDao;
import cn.edu.uts.web.domain.Bread;

public interface BreadService extends BaseDao<Bread> {
	List<Bread> getOwnAll();
	Bread findByCode(String code);
}
