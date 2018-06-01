package cn.edu.uts.web.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售表
 * @author slj
 */
public class Sell implements Serializable {
	private String id;// 销售的单号
	private Bread bread;// 销售的面包
	private Date selldate;// 销售的时间
	private Integer sellnum;//销售的数量
    private Double sellprice;//销售价格
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Bread getBread() {
		return bread;
	}
	public void setBread(Bread bread) {
		this.bread = bread;
	}

	public Date getSelldate() {
		return selldate;
	}

	public void setSelldate(Date selldate) {
		this.selldate = selldate;
	}
	public Integer getSellnum() {
		return sellnum;
	}
	public void setSellnum(Integer sellnum) {
		this.sellnum = sellnum;
	}
	public Double getSellprice() {
		return sellprice;
	}
	public void setSellprice(Double sellprice) {
		this.sellprice = sellprice;
	}
	
}
