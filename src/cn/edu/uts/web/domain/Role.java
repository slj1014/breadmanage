package cn.edu.uts.web.domain;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {
private Long id;
private String name;
private String descrpition;
private Set<User> users=new HashSet<User>();
private Set<Privilege> privileges=new HashSet<Privilege>();

public Role() {
	super();
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescrpition() {
	return descrpition;
}
public void setDescrpition(String descrpition) {
	this.descrpition = descrpition;
}
public Set<User> getUsers() {
	return users;
}
public void setUsers(Set<User> users) {
	this.users = users;
}
public Set<Privilege> getPrivileges() {
	return privileges;
}
public void setPrivileges(Set<Privilege> privileges) {
	this.privileges = privileges;
}

}
