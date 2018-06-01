package cn.edu.uts.web.domain;

import java.io.File;
import java.io.Serializable;

public class Bread implements Serializable{
    private Long id;//面包的id，也是面包的条形码
    private String code;//条形码
	private String name;// 面包的名字
	private Integer type;// 面包的类型,0表示总部面包，1表示自创面包
	private Double cost;// 成本
	private Integer day;// 保质期的天数
    private Integer deadline;//距离保质期的天数
    private String description;//面包的描述
    
    private String path;//文件保存的路径
    private String filename;//存的文件的名字UUID_老文件名
    
    //文件上传
    private File upload;
    private String uploadContentType;//上传的类型
    private String uploadFileName;//文件名
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
    
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getDeadline() {
		return deadline;
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "Bread [id=" + id + ", code=" + code + ", name=" + name
				+ ", type=" + type + ", cost=" + cost + ", day=" + day
				+ ", deadline=" + deadline + ", description=" + description
				+ ", path=" + path + ", filename=" + filename + ", upload="
				+ upload + ", uploadContentType=" + uploadContentType
				+ ", uploadFileName=" + uploadFileName + "]";
	}
	
}
