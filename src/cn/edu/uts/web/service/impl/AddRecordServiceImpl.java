package cn.edu.uts.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.uts.web.base.dao.impl.BaseDaoImpl;
import cn.edu.uts.web.domain.AddRecord;
import cn.edu.uts.web.domain.Bread;
import cn.edu.uts.web.domain.Sell;
import cn.edu.uts.web.service.AddRecordServcie;

@Service
public class AddRecordServiceImpl extends BaseDaoImpl<AddRecord> implements
		AddRecordServcie {


	@Override
	public List<AddRecord> finByRemain(Long breadid) {
		return getSession()
				.createQuery(
						"from AddRecord ar where ar.remain>0 and ar.dieline>0 and ar.bread.id=? order by ar.addtime asc")
				.setLong(0, breadid).list();
	}

	/**
	 * 批量更新
	 */
	@Override
	public void updateDieline() {
		String hql = "update AddRecord set dieline=dieline-1 where dieline>0";
		getSession().createQuery(hql).executeUpdate();
	}

	public List<AddRecord> findByDieLine() {
		return getSession().createQuery(
				"from AddRecord where dieline<3 and dieline>0").list();
	}

	@Override
	public List<AddRecord> show() {
		String hql = "select new AddRecord(ad.bread,ad.remain,ad.total) from AddRecord ad where ad.remain>0  group by ad.bread.id";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<AddRecord> findDeadNum() {
		String hql = "select new AddRecord(re.id,re.bread,re.price,re.dieline,re.addtime,re.num,re.remain,re.editprice,re.total) from AddRecord re where re.remain>0  and re.dieline<3 group by re.bread.id,re.addtime";
		return getSession().createQuery(hql).list();
	}

	@Override
	public AddRecord getById(String id) {
		return (AddRecord) getSession().get(AddRecord.class,id);
	}

	@Override
	public List<AddRecord> queryByTime(String begin, String end) {
			return getSession().createQuery(
					"from AddRecord ar where ar.addtime between '" + begin
							+ " 00:00:00' and '" + end + " 23:59:59'").list();
	}
}
