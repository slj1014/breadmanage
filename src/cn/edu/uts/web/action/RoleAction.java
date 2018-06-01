package cn.edu.uts.web.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.uts.web.base.action.BaseAction;
import cn.edu.uts.web.domain.Privilege;
import cn.edu.uts.web.domain.Role;
import cn.edu.uts.web.util.HqlHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	private Long[] privilegeIds;

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public String addUI() {
		return "saveUI";
	}

	public String add() {
		roleService.save(model);
		return "toList";
	}

	public String list() {
		new HqlHelper(Role.class).buildPageBeanForStruts2(pageNum,roleService);
		return "list";
	}

	public String editUI() {
		// 根据id的值返回信息
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}

	public String edit() {
		Role role = roleService.getById(model.getId());
		role.setDescrpition(model.getDescrpition());
		role.setName(model.getName());
		roleService.update(role);
		return "toList";
	}

	public String delete() {
		roleService.delete(model.getId());
		return "toList";
	}

	/**
	 * 设置权限页面
	 * 
	 * @return
	 */
	public String setPrivilegeUI() {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);
		List<Privilege> privilegesList = privilegeService.findTopList();
		ActionContext.getContext().put("privilegeList", privilegesList);
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for (Privilege p : role.getPrivileges()) {
			privilegeIds[index++] = p.getId();
		}
		return "setPrivilegeUI";
	}

	public String setPrivilege() {
		Role role = roleService.getById(model.getId());
		System.out.println(privilegeIds.length);
		List<Privilege> privileges = privilegeService
				.getByIds(this.privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privileges));
		roleService.save(role);
		return "toList";
	}
}
