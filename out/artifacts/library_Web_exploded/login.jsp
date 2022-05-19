<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎登录图书馆管理系统</title>
<style>
.window {
	width: 332px;
	position: absolute;
	top: 30%;
	left: 50%;
	display: block;
	z-index: 2000;
	background: #fff;
	padding: 20 0;
}
</style>
<link rel="stylesheet" href="./layui/css/layui.css">
</head>
<!-- background存放登陆页面背景图片 -->
<body>
	<script src="./layui/layui.js"></script>
	<%
		Cookie[] cookies = request.getCookies();//从cookie中获取登陆用户名和密码
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("id")) {
		id = cookie.getValue();
			}
			if (cookie.getName().equals("pwd")) {
		pwd = cookie.getValue();
			}
		}
	}
	%>
	<!-- 登录失败时弹出错误消息 -->
	<%
		Object message = session.getAttribute("message");
	if (message != null && !"".equals(message)) {
		session.setAttribute("message", "");
	%>
	<script>
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg("<%=message%>");
		});
	</script>
	<%
		}
	%>
	<div class="layui-carousel" id="test1">
		<div carousel-item>
			<div style="background-image: url(1.JPG); background-size: cover;"></div>
<%--			<div style="background-image: url(2.JPG); background-size: cover;"></div>--%>
<%--			<div style="background-image: url(3.JPG); background-size: cover;"></div>--%>
<%--			<div style="background-image: url(4.JPG); background-size: cover;"></div>--%>
		</div>
	</div>
	<!-- 登陆和注册卡片-->
	<div class="window">
		<div class="layui-row">
			<div class="layui-col-xs12">
				<div class="layui-tab layui-tab-card"
					style="width: 330px; background-color: white">
					<!-- 卡片头选项-->
					<ul class="layui-tab-title">
						<li class="layui-this">登陆</li>
						<li>注册</li>
					</ul>
					<!-- 卡片内容-->
					<div class="layui-tab-content">
						<!-- 登陆表 -->
						<div class="layui-tab-item layui-show">
							<%!String id, pwd;//用户名和密码%>
							<div class="layui-anim layui-anim-up">
								<fieldset class="layui-elem-field layui-field-title"
									style="margin-top: 20px;">
									<legend>欢迎登录图书馆管理系统</legend>
								</fieldset>
								<!-- 登陆交给login.java处理 -->
								<form action="login" class="layui-form layui-form-pane"
									method=post name=form>

									<div class="layui-form-item">
										<label class="layui-form-label">用户ID</label>
										<div class="layui-input-inline">
											<input type="text" name="id"
												value="<%=(id == null ? "" : id)%>" placeholder="请输入用户ID"
												autocomplete="off" class="layui-input">
										</div>
									</div>

									<div class="layui-form-item">
										<label class="layui-form-label">密码</label>
										<div class="layui-input-inline">
											<input type="password" name="pwd"
												value="<%=(pwd == null ? "" : pwd)%>" placeholder="请输入密码"
												autocomplete="off" class="layui-input">
										</div>
									</div>

									<div class="layui-form-item">
										<input type="radio" name="identity" lay-skin="primary"
											value="reader" title="普通用户" checked> <input
											type="radio" name="identity" lay-skin="primary" value="admin"
											title="管理员"> <input type="submit"
											class="layui-btn  layui-btn-radius" value="登陆">
									</div>
								</form>
							</div>
						</div>

						<!-- 注册表 -->
						<div class="layui-tab-item">
							<div class="layui-anim layui-anim-up">
								<fieldset class="layui-elem-field layui-field-title"
									style="margin-top: 20px;">
									<legend>普通用户注册</legend>
								</fieldset>
								<!-- 登陆交给register.java处理 -->
								<form action="register" class="layui-form layui-form-pane"
									method=post name=form>
									<div class="layui-form-item">
										<label class="layui-form-label">设置密码</label>
										<div class="layui-input-inline">
											<input type="password" name="pwd" placeholder="请输入密码"
												autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">姓名</label>
										<div class="layui-input-inline">
											<input type="text" name="name" placeholder="请输入您的姓名"
												autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">电话</label>
										<div class="layui-input-inline">
											<input type="text" name="tel" placeholder="请输入您的电话"
												autocomplete="off" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">性別</label>
										<div class="layui-input-block">
											<input type="radio" name="sex" value="男" title="男" checked>
											<input type="radio" name="sex" value="女" title="女">
										</div>
									</div>




									<div class="layui-form-item">
										<input type="submit"
											class="layui-btn  layui-btn-radius layui-btn-fluid "
											value="注册">
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script>
		layui.use('form', function() {
			var form = layui.form;
			form.render();
			//监听提交
			form.on('submit(formDemo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				return false;
			});
		});
		layui.use('element', function() {
			var element = layui.element;
		});
		layui.use('carousel', function() {
			var carousel = layui.carousel;
			//建造实例
			carousel.render({
				elem : '#test1',
				width : '100%', //设置容器宽度		
				height : '700px',
				autoplay : 'true',
				interval : 5000,
				arrow : 'hover' //始终显示箭头
			//,anim: 'updown' //切换动画方式
			});
		});
	</script>
</body>
</html>