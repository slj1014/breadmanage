<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>入库明细列表</title>
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
				入库明细
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align="CENTER" valign="MIDDLE" id="TableTitle">
				  <td>面包名称</td>
				  <td>面包价格</td>
				  <td>面包数量</td>
				  <td>入库时间</td>
				  <td>操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer" datakey="breadList">

				<s:iterator value="#list">
					<tr class="TableDetail1 template">
						<td>${bread.name}</td>
						<td>${editprice}</td>
						<td>${total}&nbsp;</td>
						<td><c:formatDate value="${addtime}" /></td>
						<td>
						<s:a action="addRecordAction_editpriceUI?id=%{id}">
						        修改价格
						</s:a>
						</td>
					</tr>
				</s:iterator>

			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" style="height:40px">
		</div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>
