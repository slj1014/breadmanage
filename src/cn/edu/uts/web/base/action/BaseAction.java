package cn.edu.uts.web.base.action;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.edu.uts.web.domain.User;
import cn.edu.uts.web.service.AddRecordServcie;
import cn.edu.uts.web.service.BreadService;
import cn.edu.uts.web.service.PrivilegeService;
import cn.edu.uts.web.service.RoleService;
import cn.edu.uts.web.service.SellService;
import cn.edu.uts.web.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	@Resource
	protected  UserService userService;
	
	@Resource
	protected  RoleService roleService;
	
	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected BreadService breadService;
	
	@Resource
	protected AddRecordServcie addRecordService;
	
	@Resource
	protected SellService sellService;
	
	
	protected T model;
	
	protected int pageNum=1;//当前页面的页号,默认是1

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public BaseAction() {
		try {
			// 得到model的类型信息
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];

			// 通过反射生成model的实例
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}
	

}
