package cn.edu.uts.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.uts.web.base.action.BaseAction;
import cn.edu.uts.web.domain.Bread;
import cn.edu.uts.web.domain.User;
import cn.edu.uts.web.util.HqlHelper;
import cn.edu.uts.web.util.WebUtil;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class BreadAction extends BaseAction<Bread> {

	public String addUI() {
		return "saveUI";
	}

	public String add() {
		// 判断当前用户是否管理员，如果是管理员就是添加的面包就是总部面包
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if ("admin".equals(user.getLoginName())) {
			model.setType(0);
		} else {
			model.setType(1);
		}
		model.setDeadline(model.getDay());
		// 当读处理文件
		this.model.setFilename(UUID.randomUUID().toString() + "_"
				+ this.model.getUploadFileName());
		// 得到file存放的目录的真是路径
		String storePath = ServletActionContext.getServletContext()
				.getRealPath("/file");
		// 计算存放的子路径
		this.getModel().setPath(
				WebUtil.makeDirs(storePath, this.getModel().getFilename()));
		File file = new File(storePath + "\\" + this.model.getPath() + "\\"
				+ this.model.getFilename());
		try {
			FileUtils.copyFile(this.model.getUpload(), file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		breadService.save(model);
		return getRturnValue();
	}

	public String list() {
		new HqlHelper(Bread.class,"b").addCondition("b.type=?",0).buildPageBeanForStruts2(pageNum, breadService);
		return "list";
	}

	public String ownList() {
		new HqlHelper(Bread.class,"b").addCondition("b.type=?",1).buildPageBeanForStruts2(pageNum, breadService);
		return "ownList";
	}

	public String delete() {
		breadService.delete(model.getId());
		return "toList";
	}

	public String editUI() {
		Bread bread = breadService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(bread);
		return "saveUI";
	}

	public String edit() {
		Bread bread = breadService.getById(model.getId());
		bread.setCode(model.getCode());
		bread.setCost(model.getCost());
		bread.setDay(model.getDay());
		bread.setDescription(model.getDescription());
		bread.setName(bread.getName());
		breadService.update(bread);
		return getRturnValue();
	}
	

	public String getRturnValue() {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		String returnValue = "";
		if ("admin".equals(user.getLoginName())) {
			returnValue = "toList";
		} else {
			returnValue = "toOwnList";
		}
		return returnValue;
	}
	
}
