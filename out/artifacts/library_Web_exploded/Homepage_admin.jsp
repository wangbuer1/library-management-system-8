<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>图书管理系统</title>
<link rel="stylesheet" href="./layui/css/layui.css">
</head>
<body class="layui-layout-body">
	<!-- 显示成功登陆信息 -->
	<%
		Object success = session.getAttribute("success");

	if (success != null && !"".equals(success)) {
		session.setAttribute("success", "");
	%>
	<script src="./layui/layui.js"></script>
	<script>
    layui.use('layer', function(){
  var layer = layui.layer;
  layer.msg("<%=success%>");
		});
	</script>
	<%
		}
	%>
	<!-- 水平导航 -->
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<!-- logo -->
			<div class="layui-logo" style="font-size: 20px;">图书管理系统</div>
			<!-- 輸出用戶名 -->
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><i
					class="layui-icon layui-icon-username"
					style="font-size: 20px; color: #c2c2c2;"><%=session.getAttribute("name")%></i>
				</li>
			</ul>
		</div>
		<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;"><i class="layui-icon layui-icon-read"
							style="font-size: 20px; color: #c2c2c2;">图书管理</i></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="search?all_book=true" target="main"><i
									class="layui-icon layui-icon-search"
									style="font-size: 20px; color: #c2c2c2;">查询图书</i></a>
							</dd>
							<dd>
								<a href="add_book.jsp" target="main"><i
									class="layui-icon layui-icon-add-1"
									style="font-size: 20px; color: #c2c2c2;">增加图书</i></a>
							</dd>
						</dl></li>
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;"><i class="layui-icon layui-icon-user"
							style="font-size: 20px; color: #c2c2c2;">用户管理</i></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="punishment.jsp" target="main"><i
									class="layui-icon layui-icon-set-fill"
									style="font-size: 20px; color: #c2c2c2;">违规处理</i></a>
							</dd>
							<dd>
								<a href="manage_rights.jsp" target="main"><i
									class="layui-icon layui-icon-auz"
									style="font-size: 20px; color: #c2c2c2;">权限管理</i></a>
							</dd>
							<dd>
								<a href="search?id=0" target="main"><i
									class="layui-icon layui-icon-group"
									style="font-size: 20px; color: #c2c2c2;">读者管理</i></a>
							</dd>
							<dd>
								<a href="search?search_user=<%=session.getAttribute("id")%>"
									target="main"><i class="layui-icon layui-icon-edit"
									style="font-size: 20px; color: #c2c2c2;">个人信息</i></a>
							</dd>
						</dl></li>

					<li class="layui-nav-item"><a href="login.jsp"><i
							class="layui-icon layui-icon-logout"
							style="font-size: 20px; color: #c2c2c2;">退出登录</i></a></li>
				</ul>
			</div>
		</div>

		<!-- 内容主体区域 -->
		<div class="layui-body">
			<!-- 窗口，登陆后显示模块 -->
			<iframe name="main" id="demoAdmin" src="search?all_book=true"
				frameborder="0" style="width: 100%; height: 100%;"></iframe>
		</div>
		<!-- 底部 -->
		<div class="layui-footer">欢迎使用图书管理系统</div>
	</div>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</body>
</html>