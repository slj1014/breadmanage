package cn.edu.uts.web.action;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.cglib.transform.impl.AddStaticInitTransformer;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.edu.uts.web.base.action.BaseAction;
import cn.edu.uts.web.domain.AddRecord;
import cn.edu.uts.web.domain.Bread;
import cn.edu.uts.web.domain.Sell;
import cn.edu.uts.web.util.ExcleUtils;
import cn.edu.uts.web.util.HqlHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class AddRecordAction extends BaseAction<AddRecord> {
	private Long breadid;
	private String begin;
	private String end;

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Long getBreadid() {
		return breadid;
	}

	public void setBreadid(Long breadid) {
		this.breadid = breadid;
	}

	public String querytime;

	public String getQuerytime() {
		return querytime;
	}

	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}

	public String addUI() {
		List<Bread> breads = breadService.getAll();
		ActionContext.getContext().put("breads", breads);
		return "saveUI";
	}

	public String add() {
		Bread bread = breadService.getById(breadid);
		model.setBread(bread);
		model.setAddtime(new Date());
		model.setDieline(bread.getDeadline());
		model.setRemain(model.getNum());
		model.setEditprice(model.getPrice());
		addRecordService.save(model);
		return "toSave";
	}

	public String list() {
		if (querytime == null || querytime.trim().equals("")) {
			new HqlHelper(AddRecord.class, "ad").addOrder("addtime", false)
					.buildPageBeanForStruts2(pageNum, addRecordService);
		} else {
			// select * from addrecord where addtime between '2015-05-13
			// 00:00:00' and '2015-05-13 23:59:59';
			System.out.println(querytime);
			new HqlHelper(AddRecord.class, "ad")
					.addCondition(
							"ad.addtime between '" + querytime
									+ " 00:00:00' and '" + querytime
									+ " 23:59:59'")
					.addOrder("ad.addtime", false)
					.buildPageBeanForStruts2(pageNum, addRecordService);
		}
		return "list";
	}

	public String repertorylist() {
		List<AddRecord> list = addRecordService.show();
		ActionContext.getContext().put("list", list);
		return "repertory";
	}

	public String deadlinelist() {
		List<AddRecord> list = addRecordService.findDeadNum();
		ActionContext.getContext().put("list", list);
		return "deadline";
	}

	public String editprice() {
		AddRecord addRecord = addRecordService.getById(model.getId());
		addRecord.setEditprice(model.getEditprice());
		addRecordService.update(addRecord);
		return "todeadline";
	}

	public String editpriceUI() {
		AddRecord addRecord = addRecordService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(addRecord);
		return "editprice";
	}

	public void print() {
		String fname = "入库明细";
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		try {
			OutputStream os = response.getOutputStream();
			// 取得输出流
			response.reset();// 清空输出流
			// 下面是对中文文件名的处理
			response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
			fname = new String(fname.getBytes("UTF-8"), "ISO8859-1");// firefox浏览器
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fname + ".xls");
			response.setContentType("application/msexcel");// 定义输出类型
			List<AddRecord> addRecords=addRecordService.queryByTime(begin,end);
			ExcleUtils.createAddExcle(os, begin, end, addRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
