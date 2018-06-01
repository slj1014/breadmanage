package cn.edu.uts.web.install;

import javax.annotation.Resource;

import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.uts.web.domain.Privilege;
import cn.edu.uts.web.domain.User;
import cn.edu.uts.web.util.MD5Util;

@Component
public class Install {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// ===================================================
		// 一、超级管理员
		User user = new User();
		user.setName("超级管理员");
		user.setLoginName("admin");
		user.setPassword(MD5Util.GetMD5Code("admin")); // 要使用MD5摘要
		session.save(user); // 保存

		// ===================================================
		// 二、权限数据
		Privilege menu, menu1, menu2, menu3;
		menu = new Privilege("系统管理", null, "FUNC20082.gif", null);
		menu1 = new Privilege("角色管理", "roleAction_list", null, menu);
		menu2 = new Privilege("用户管理", "userAction_list", null, menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);

		session.save(new Privilege("角色列表", "roleAction_list", null, menu1));
		session.save(new Privilege("角色删除", "roleAction_delete", null, menu1));
		session.save(new Privilege("角色添加", "roleAction_add", null, menu1));
		session.save(new Privilege("角色修改", "roleAction_edit", null, menu1));

		session.save(new Privilege("用户列表", "userAction_list", null, menu2));
		session.save(new Privilege("用户删除", "userAction_delete", null, menu2));
		session.save(new Privilege("用户添加", "userAction_add", null, menu2));
		session.save(new Privilege("用户修改", "userAction_edit", null, menu2));
		session.save(new Privilege("用户初始化密码", "userAction_initPassword", null,
				menu2));
		// ========面包管理
		menu = new Privilege("面包管理", null, "FUNC20057.gif", null);
		menu1 = new Privilege("总部面包管理", "breadAction_list", null, menu);
		menu2 = new Privilege("自创面包管理", "breadAction_ownList", null, menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(new Privilege("添加面包", "breadAction_add", null, menu1));
		session.save(new Privilege("删除面包", "breadAction_delete", null, menu1));
		session.save(new Privilege("修改面包", "breadAction_edit", null, menu1));

		session.save(new Privilege("添加面包", "breadAction_add", null, menu2));
		session.save(new Privilege("删除面包", "breadAction_delete", null, menu2));
		session.save(new Privilege("修改面包", "breadAction_edit", null, menu2));

		// ====报表管理
		menu = new Privilege("报表管理", null, "FUNC20064.gif", null);
		menu1 = new Privilege("入库明细", "addRecordAction_list", null, menu);
		menu2 = new Privilege("销售明细", "sellAction_list", null, menu);
		session.save(new Privilege("打印销售明细", "sellAction_print", null, menu2));
		session.save(new Privilege("打印入库明细","addRecordAction_print", null, menu1));
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		// 仓库管理
		// ======
		menu = new Privilege("仓库管理", null, "FUNC20069.gif", null);
		menu1 = new Privilege("库存管理", "addRecordAction_repertorylist", null, menu);
		menu2 = new Privilege("即将过期面包管理", "addRecordAction_deadlinelist", null,
				menu);
		menu3 = new Privilege("入库操作", "addRecordAction_addUI", null, menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		// 销售管理
		menu = new Privilege("销售管理", null, "FUNC20007.gif", null);
		menu1 = new Privilege("销售面包", "sellAction_addUI", null, menu);
		session.save(menu);
		session.save(menu1);
	}

	public static void main(String[] args) {
		System.out.println("正在执行安装...");

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/applicationContext.xml");
		Install installer = (Install) ac.getBean("install");
		installer.install();

		System.out.println("== 安装完毕 ==");
	}
}
