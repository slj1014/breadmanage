package cn.edu.uts.web.service;

import java.util.Date;
import java.util.List;

import cn.edu.uts.web.base.dao.BaseDao;
import cn.edu.uts.web.domain.Sell;

public interface SellService extends BaseDao<Sell>{


	List<Sell> queryByTime(String begin, String end);
}
