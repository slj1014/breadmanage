package cn.edu.uts.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.uts.web.base.dao.impl.BaseDaoImpl;
import cn.edu.uts.web.domain.Bread;
import cn.edu.uts.web.service.BreadService;
@Service
public class BreadServiceImpl extends BaseDaoImpl<Bread> implements BreadService{
/**
 * 重写getAll方法
 */
	@Override
	public List<Bread> getAll() {
		return getSession().createQuery("from Bread where type=0").list();
	}
	
	/**
	 * 获取自创面包
	 * @return
	 */
	public List<Bread> getOwnAll(){
		return getSession().createQuery("from Bread where type=1").list();
	}

	@Override
	public Bread findByCode(String code) {
		return (Bread) getSession().createQuery("from Bread where code=?").setString(0, code).uniqueResult();
	}

}
