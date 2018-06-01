<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>销售细列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/pageCommon.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/PageUtils.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/jquery_date/laydate.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
<script type="text/javascript">
    </script>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
			销售明细
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align="CENTER" valign="MIDDLE" id="TableTitle">
					<td width="200px">销售时间</td>
					<td>面包名字</td>
					<td>销售数量</td>
					<td>销售的价格</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="breadList">

				<s:iterator value="recordList">
					<tr class="TableDetail1 template">
						<td><c:formatDate value="${selldate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${bread.name}</td>
						<td>${sellnum}</td>
						<td>${sellprice}</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" style="height:40px;float:left;">
			<div id="TableTail_inside">
				<form action="${pageContext.request.contextPath}/sellAction_list.action">
					<table>
						<tr>
							<td>日期：</td>
							<td><input id="hello" class="laydate-icon" name="querytime"></td>
							<td><input type="submit" value="查询" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	<!--  打印报表-->
		<div style="height:40px;float:right;margin-top: -50px">
			<div id="TableTail_inside">
				<form action="${pageContext.request.contextPath}/sellAction_print.action">
					<table>
						<tr>
							<td>开始日期：</td>
						<td><input id="begin" class="laydate-icon" name="begin"></td>
						<td>结束时间:</td>
						<td><input id="end" class="laydate-icon" name="end"></td>
							<td><input type="submit" value="打印EXCLE" /></td>
					</table>
				</form>
			</div>
		</div>
	<div>
	<!--分页信息-->
	<s:form action="sellAction_list?id=%{id}">
	</s:form>
	<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>
	</div>
</body>
<script type="text/javascript">
laydate({
    elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
});
laydate({
    elem: '#begin', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
});
laydate({
    elem: '#end', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
});
</script>
</html>
