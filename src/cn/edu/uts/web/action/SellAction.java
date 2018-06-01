package cn.edu.uts.web.action;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
public class SellAction extends BaseAction<Sell> {
	private String breadcode;
	private String querytime;
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

	public String getQuerytime() {
		return querytime;
	}

	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}

	public String getBreadcode() {
		return breadcode;
	}

	public void setBreadcode(String breadcode) {
		this.breadcode = breadcode;
	}

	public String addUI() {
		return "saveUI";
	}

	public String add() {
		Bread bread = breadService.findByCode(breadcode);
		model.setBread(bread);
		model.setSelldate(new Date());
		// 更新入库明细的剩余数量
		List<AddRecord> addRecords = addRecordService
				.finByRemain(bread.getId());
		for (int i = 0; i < addRecords.size(); i++) {
			AddRecord addRecord = addRecords.get(i);
			int remain = addRecord.getRemain();
			System.out.println(remain + "======" + model.getSellnum());
			// 如果第一条记剩余数大于销售数
			if (remain > model.getSellnum() || remain == model.getSellnum()) {
				addRecord.setRemain(remain - model.getSellnum());
				addRecordService.update(addRecord);
				//插入销售表
				model.setSellprice(addRecord.getEditprice());
				sellService.save(model);
				break;
			} else {
				addRecord.setRemain(0);
				addRecordService.update(addRecord);
				model.setSellprice(addRecord.getEditprice());
				sellService.save(model);
				// 下一个sellnum就是相减后的结果
				model.setSellnum(model.getSellnum() - remain);
			}
		}
		return "saveUI";
	}

	public String list() {
		if (querytime == null || querytime.trim().equals("")) {
			new HqlHelper(Sell.class, "s").addOrder("s.selldate", false)
					.buildPageBeanForStruts2(pageNum, sellService);
		} else {
			new HqlHelper(Sell.class, "s")
					.addCondition("s.selldate between '"+querytime+" 00:00:00' and '"+querytime+" 23:59:59'")
					.addOrder("s.selldate", false)
					.buildPageBeanForStruts2(pageNum, sellService);
		}
		return "list";
	}
	
	public void print(){
		 String fname = "销售明细";
		   HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);  
			try {
				OutputStream os = response.getOutputStream();
				//取得输出流
			    response.reset();//清空输出流
			    //下面是对中文文件名的处理
			    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
			    fname = new String(fname.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
			    response.setHeader("Content-Disposition","attachment;filename="+fname+".xls");
			    response.setContentType("application/msexcel");//定义输出类型
				List<Sell> sells=sellService.queryByTime(begin,end);
			    ExcleUtils.createSellExcle(os, begin, end, sells);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
