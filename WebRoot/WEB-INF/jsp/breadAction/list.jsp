<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>总部面包列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 面包管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">面包图片</td>
                <td>面包名称</td>
                <td>条形码</td>
                 <td>面包说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="breadList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td><a href="${pageContext.request.contextPath}/breadAction_showMessage.action?id=${id}"><img src="${pageContext.request.contextPath}/file/${path}/${filename}"  style="width: 100px;height: 100px"></a></td>
				<td>${name}&nbsp;</td>
				<td>${code}</td>
				<td>${description}&nbsp;</td>
				<td>
					<s:a action="breadAction_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
					<s:a action="breadAction_editUI?id=%{id}">修改</s:a>
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="breadAction_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
  	<!--分页信息-->
		<s:form action="breadAction_list?id=%{id}"> </s:form>
		<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>
</body>
</html>
