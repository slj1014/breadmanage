<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>面包信息</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 面包信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<!--显示表单内容-->
<div id=MainArea>

    <s:form action="breadAction_%{id==null?'add' : 'edit'}" enctype="multipart/form-data">
    <s:token></s:token>
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 面包信息 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td>条形码:</td>
                        <td><s:textfield name="code" cssClass="InputStyle required"/> *
							（编号唯一）
						</td>
                    </tr>
                    <tr><td>面包名字</td>
                        <td><s:textfield name="name" cssClass="InputStyle required"/> *</td>
                    </tr>
                     <tr><td>成本:</td>
                        <td><s:textfield name="cost" cssClass="InputStyle required"/> *
						</td>
                    </tr>
                       <s:if test="%{id!=null}">
                 </s:if>
                    <tr><td>保质天数:</td>
                        <td><s:textfield name="day" cssClass="InputStyle"/>(天)</td>
                    </tr>
                    <s:if test="%{id==null}">
                       <tr><td>面包图片:</td>
                        <td><s:file name="upload"></s:file></td>
                    </tr>
                    </s:if>
                    <tr><td>产品描述:</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	说明：<br />
	1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
	2，新建用户后，密码被初始化为"1234"。<br />
	3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
	4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
	5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
	6，修改面包信息时，登录名不可修改。
</div>

</body>
</html>
