package cn.edu.uts.web.action;

import java.util.HashSet;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.uts.web.base.action.BaseAction;
import cn.edu.uts.web.domain.AddRecord;
import cn.edu.uts.web.domain.Role;
import cn.edu.uts.web.domain.User;
import cn.edu.uts.web.service.UserService;
import cn.edu.uts.web.util.MD5Util;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private Long[] roleIds;// 页面role的id
	private String oldPassword;// 旧密码

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public String list() {
		List<User> users = userService.getAll();
		// 将集合放入对象栈
		ActionContext.getContext().put("users", users);
		return "list";
	}

	public String addUI() {
		// 准备role的数据
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roles", roleList);
		return "saveUI";
	}

	public String add() {
		List<Role> roles = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roles));
		model.setPassword(MD5Util.GetMD5Code("1234"));
		userService.save(model);
		return "toList";
	}

	public String editUI() {
		// 根据id返回user对象
		User user = userService.getById(model.getId());
		// 准备role的数据
		List<Role> roleList = roleService.getAll();
		ActionContext.getContext().put("roles", roleList);
		// 放入值栈栈顶回显数据
		ActionContext.getContext().getValueStack().push(user);
		if (user.getRoles().size() > 0) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}

	public String edit() {
		// 从数据库中取出原来的对象
		User user = userService.getById(model.getId());
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		// 这是属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setDescription(model.getDescription());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		userService.update(user);
		return "toList";
	}

	public String delete() {
		userService.delete(model.getId());
		return "toList";
	}

	public String login() {
		User user = userService
				.login(model.getLoginName(), model.getPassword());
		if (user == null) {
			this.addFieldError("login", "用户名密码不正确");
			return "loginUI";
		} else {
			ActionContext.getContext().getSession().put("user", user);

			// 查看是存在即将过期的面包
			List<AddRecord> addRecords = addRecordService.findByDieLine();
			if (addRecords.size()>0) {
				// 1.存在即将过期的面包
				ServletActionContext.getRequest().getSession()
						.setAttribute("yes", "yes");
			}
			return "toIndex";
		}
	}

	/**
	 * 修改密码
	 */
	public String setPasswordUI() {
		return "setPasswordUI";
	}

	public String setPassword() {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user.getPassword().equals(MD5Util.GetMD5Code(oldPassword))) {
			User user1 = userService.getById(user.getId());
			user1.setPassword(MD5Util.GetMD5Code(model.getPassword()));
			userService.update(user1);
			return "success";
		} else {
			this.addFieldError("login", "用户名密码不正确");
			return "setPasswordUI";
		}
	}

	/** 初始化密码为“1234” */
	public String initPassword() {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性（要使用MD5摘要）
		String passwdMD5 = MD5Util.GetMD5Code("1234");
		user.setPassword(passwdMD5);

		// 3，更新到数据库
		userService.update(user);

		return "toList";

	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	public String loginout() {
		ActionContext.getContext().getSession().remove("user");
		ActionContext.getContext().getSession().remove("yes");
		return "loginUI";
	}
}
