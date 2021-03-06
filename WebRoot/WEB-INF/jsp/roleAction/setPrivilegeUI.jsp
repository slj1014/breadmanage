<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>配置权限</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/file.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
<script type="text/javascript">
	$(function() {
		//该所有的权限复选框添加事件
		$("[name='privilegeIds']").click(
				function() {
					//自己选中或者取消的时，把所有的夏季权限也都同时选中或取消
					$(this).siblings("ul").find("input").attr("checked",
							this.checked);
					//当选中其中的一个权限时，也要同时选中所有的直系上级权限
					if (this.checked) {
						$(this).parents("li").children("input").attr("checked",
								true);
						//当取消一个权限时，统计没有选中的权限了，就取消他的上级权限，在向上也是这样做
					} else {
					if( $(this).parent().siblings("li").children("input:checked").size() == 0 ){
						$(this).parent().parent().siblings("input").attr("checked", false);
						
						var start = $(this).parent().parent();
						if(start.parent().siblings("li").children("input:checked").size() == 0 ){
							start.parent().parent().siblings("input").attr("checked", false);
						}
					}
				}
				});
	});
</script>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				配置权限
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>

		<s:form action="roleAction_setPrivilege">
			<s:hidden name="id"></s:hidden>

			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					正在为【${role.name}】配置权限
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<!--表头-->
						<thead>
							<tr align="LEFT" valign="MIDDLE" id="TableTitle">
								<td width="300px" style="padding-left: 7px;">
									<!-- 全选 --> <input type="checkbox"
									onClick="$('[name=privilegeIds]').attr('checked', this.checked)" />
									<label for="cbSelectAll">全选</label>
								</td>
							</tr>
						</thead>

						<!--显示数据列表-->
						<tbody id="TableData">
							<tr class="TableDetail1">
								<!-- 显示权限树 -->
								<td>
									<ul id="root">
										<s:iterator value="#privilegeList">
											<li><input type="checkbox" name="privilegeIds"
												value="${id}" id="cb_${id}"" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/>/><label for="cb_${id}"><span
													class="folder">${name}</span></label>
												<ul>
													<s:iterator value="children">
														<li><input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked':''}"/>/> <!--label的作用：点字也可以选中复选框  -->
															<label for="cb_${id}"><span class="folder">${name}</span></label>
															<ul>
																<s:iterator value="children">
																	<li><input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked':''}"/>/>
																		<label for="cb_${id}"><span class="folder">${name}</span></label></li>
																</s:iterator>
															</ul></li>
													</s:iterator>
												</ul>
												</li>
										</s:iterator>
									</ul>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>
	<script type="text/javascript">
		$("#root").treeview();
	</script>
	<div class="Description">
		说明：<br /> 1，选中一个权限时：<br /> &nbsp;&nbsp;&nbsp;&nbsp; a，应该选中他的所有直系上级。<br />
		&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br /> 2，取消选择一个权限时：<br />
		&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择他的所有直系下级。<br />
		&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并向上做这个操作。<br />

		3，全选/取消全选。<br /> 4，默认选中当前岗位已有的权限。<br />
	</div>

</body>
</html>
