package cn.edu.uts.web.domain;

import java.io.Serializable;
import java.util.Date;

public class AddRecord implements Serializable {
	private String id;// 入库单号
	private Bread bread;
	private Double price;// 价格
	private Integer dieline;// 距离过期的天数
	private Date addtime;// 入库时间
	private Integer num;// 入库的数量
	private Integer remain;// 批次剩余的数量
	private Double editprice;// 修改后的价格
    private Integer total=0;
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getEditprice() {
		return editprice;
	}

	public void setEditprice(Double editprice) {
		this.editprice = editprice;
	}

	public Integer getRemain() {
		return remain;
	}

	public void setRemain(Integer remain) {
		this.remain = remain;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

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

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDieline() {
		return dieline;
	}

	public void setDieline(Integer dieline) {
		this.dieline = dieline;
	}

	public AddRecord() {
		super();
	}

	public AddRecord(Bread bread, Integer remain, Integer total) {
		super();
		this.bread = bread;
		this.remain = remain;
		this.total+=this.remain;
	}
	

	public AddRecord(String id, Bread bread, Double price, Integer dieline,
			Date addtime, Integer num, Integer remain, Double editprice,
			Integer total) {
		super();
		this.id = id;
		this.bread = bread;
		this.price = price;
		this.dieline = dieline;
		this.addtime = addtime;
		this.num = num;
		this.remain = remain;
		this.editprice = editprice;
		this.total+=this.remain;
	}

	@Override
	public String toString() {
		return "AddRecord [bread=" + bread + ", remain=" + remain + ", total="
				+ total + "]";
	}


}
