package cn.edu.uts.web.base.dao;

import java.util.List;

import cn.edu.uts.web.domain.PageBean;
import cn.edu.uts.web.util.HqlHelper;

public interface BaseDao<T> {
	/**
	 * 保存实体
	 */
   public void save(T entity);
   /**
    * 删除实体
    */
   public void delete(Long id);
   /**
    * 更新实体
    */
   public void update(T entity);
   /**
    * 根据Id查询实体
    */
   public T getById(Long id);
   /**
    * 根据id数组查询实体
    */
   public List<T> getByIds(Long[] ids);
   /**
    * 查询所有的实体
    */
   public List<T> getAll();
   
   /**
    * 获取分页bean
    * @param pageNum
    * @param hqlHelper
    * @return
    */
   public PageBean getPageBean(int pageNum, HqlHelper hqlHelper);
}
