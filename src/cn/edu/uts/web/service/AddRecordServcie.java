package cn.edu.uts.web.service;

import java.util.Date;
import java.util.List;

import cn.edu.uts.web.base.dao.BaseDao;
import cn.edu.uts.web.domain.AddRecord;

public interface AddRecordServcie extends BaseDao<AddRecord> {

	/**
	 * 返回还没过保质期的且数量大于1的集合
	 * 
	 * @param breadid
	 * @return
	 */
	List<AddRecord> finByRemain(Long breadid);

	/**
	 * 更新面包的距离过期的时间
	 */
	void updateDieline();

	/**
	 * 查询即将过期的面包
	 * @return
	 */
	List<AddRecord> findByDieLine();
	
	/**
	 * 显示仓库的信息
	 * @return
	 */
	List<AddRecord> show();

	/**
	 * 显示过期面包信息
	 * @return
	 */
	List<AddRecord> findDeadNum();
	
	/**
	 * 重写get方法
	 * @param id
	 * @return
	 */
    AddRecord getById(String id);

	List<AddRecord> queryByTime(String begin, String end);
}
