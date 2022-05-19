<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,DB.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>

</head>
<body bgcolor=#F2F2F2>
	<!-- 弹出错误消息 -->
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
	<%
	
	reader a=(reader)session.getAttribute("person_info");
	String user = null;
	String password = null;
	String name = null;
	String sex = null;
	String tel = null;
	user = String.valueOf(a.getReader_id());
	password = a.getPwd();
	name = a.getReader_name();
	sex = a.getReader_sex();
	if(sex!=null&&sex.equals("女")) sex="checked";
	tel = a.getReader_tel();
	%>
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 30px;">
					<legend>个人信息</legend>
				</fieldset>
				<form class="layui-form" method="post"
					action="register?id=<%=user%>">
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label">ID</label>
						<div class="layui-input-block">
							<input name="_id" class="layui-input" type="text" value=<%=user%>
								name="admin_id" autocomplete="off" lay-verify="title">
						</div>
					</div>
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-block">
							<input name="pwd" class="layui-input" type="text"
								value=<%=password%> name="admin_id" autocomplete="off"
								lay-verify="title">
						</div>
					</div>
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-block">
							<input name="name" class="layui-input" type="text"
								value=<%=name%> name="admin_id" autocomplete="off"
								lay-verify="title">
						</div>
					</div>
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label">联系方式</label>
						<div class="layui-input-block">
							<input name="tel" class="layui-input" type="text" value=<%=tel%>
								name="admin_id" autocomplete="off" lay-verify="title">
						</div>
					</div>
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label">性别</label>
						<div class="layui-input-block">
							<input type="radio" name="sex" value="男" title="男" checked>
							<input type="radio" name="sex" value="女" title="女" <%=sex%>>
							<input type="submit" value="保存" class="layui-btn  ">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<hr>


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
	</script>
</body>
</html>