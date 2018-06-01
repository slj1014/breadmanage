package cn.edu.uts.web.domain;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.opensymphony.xwork2.ActionContext;

public class User implements Serializable {
	private Long id;//id值
	private String loginName; // 登录名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String description; // 说明
	private Set<Role> roles=new HashSet<Role>();
	public User() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	//==================判断权限
		public boolean hasPrivilegeByName(String privilgegName){
			//超级管理员
			if(isAdmin()){
				return true;
			}
			//一般用户
			for(Role role:roles){
				for(Privilege privilege:role.getPrivileges()){
					if(privilege.getName().equals(privilgegName)){
						return true;
					}
				}
			}
			return false;
		}
		/**
		 * 判断本用户是否有指定URL的权限
		 * @param privilegeUrl
		 * @return
		 */
		public boolean hasPrivilegeByUrl(String privilegeUrl) {
			// 超级管理员有所有的权限
			if (isAdmin()) {
				return true;
			}

			// 如果以UI后缀结尾，就去掉UI后缀，以得到对应的权限（例如：addUI与add是同一个权限）
			if (privilegeUrl.endsWith("UI")) {
				privilegeUrl = privilegeUrl.substring(0, privilegeUrl.length() - 2);
			}
			//其他用户要是有权限才返回true
			List<String> allPrivilegeUrls=(List<String>) ActionContext.getContext().getApplication().get("allPrivileges");
			if(!allPrivilegeUrls.contains(privilegeUrl)){
				//如果是不需要控制的功能，则所有用户都可以用
				return true;
			}else{
				// 如果是要控制的功能，则有权限才能使用
				for (Role role : roles) {
					for (Privilege privilege : role.getPrivileges()) {
						if (privilegeUrl.equals(privilege.getUrl())) {
							return true;
						}
					}
				}
			}
				return false;
		}
		private boolean isAdmin() {
			// TODO Auto-generated method stub
			return "admin".equals(loginName);
		}
}
