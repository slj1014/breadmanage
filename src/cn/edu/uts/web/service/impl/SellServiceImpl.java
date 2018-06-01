package cn.edu.uts.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.uts.web.base.dao.impl.BaseDaoImpl;
import cn.edu.uts.web.domain.Sell;
import cn.edu.uts.web.service.SellService;

@Service
public class SellServiceImpl extends BaseDaoImpl<Sell> implements SellService {
  
	/**
	 * 根据时间查询出集合
	 */
	@Override
	public List<Sell> queryByTime(String begin, String end) {
		return getSession().createQuery(
				"from Sell s where s.selldate between '" + begin
						+ " 00:00:00' and '" + end + " 23:59:59'").list();

	}

}
