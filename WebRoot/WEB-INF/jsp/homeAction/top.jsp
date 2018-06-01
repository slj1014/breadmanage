<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>TopMenu</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
	<LINK href="${pageContext.request.contextPath}/style/blue/top.css" type=text/css rel=stylesheet>
	
	<script type="text/javascript">
	</script>
	<style type="text/css">
		#messageArea{
			color: white;
			font-size: 14px;
			font-weight: bold;
		}
	</style>
</head>

<body CLASS=PageBody leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
	
	<div id="Head1">
		<div id="Logo">
			<a id="msgLink" href="javascript:void(0)"></a>
            <font color="#0000CC" style="color:#F1F9FE; font-size:28px; font-family:Arial">面包管系统</font> 
			<!--<img border="0" src="css/blue/images/logo.png" />-->
        </div>
		<div id="Head1Right">
			<div id="Head1Right_UserName">
                <img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user.gif" /> 您好，<b>${user.name }</b>
			</div>
			<div id="Head1Right_UserDept"></div>
			<div id="Head1Right_UserSetup">
            	<s:a action="userAction_setPasswordUI" target="right"><img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user_setup.gif" />修改密码</s:a>
			</div>
			<div id="Head1Right_Time">
				</div>
		</div>
        <div id="Head1Right_SystemButton">
            <a href="${pageContext.request.contextPath}/userAction_loginout.action" target="_parent">
                <img width="78" height="20" alt="退出系统" src="${pageContext.request.contextPath}/style/blue/images/top/logout.gif" />
            </a>
        </div>
        <div id="Head1Right_Button">
            <a target="desktop" href="javascript:void(0)"></a>
        </div>
	</div>
    
    <div id="Head2">
        <div id="Head2_Awoke">
        <c:if test="${!empty yes}">
        <embed src="${pageContext.request.contextPath}/sound/a.mp3"
					id="sound" width="0" height="0" />
            <marquee scrollamount="3" direction="right">
                  <font style="color: red" size="4px">你有面包要过期了！！！</font>
              </marquee>
        </c:if>
              
        </div>
        
        <div id="Head2_FunctionList" style="text-align: left">
        </div>
    </div>

</body>

</html>
