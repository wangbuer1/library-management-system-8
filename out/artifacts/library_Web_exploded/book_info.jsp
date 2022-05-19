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
	<%
		String publisher = null;
	String book_name = null;
	String author = null;
	String other_info = null;
	book a = (book) session.getAttribute("searched_book");
	if (a != null) {
		book_name = a.getBook_name();
		publisher = a.getPublisher();
		author = a.getAuthor();
		other_info = a.getOther_info();
	}
	%>
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 30px;">
					<legend>修改图书</legend>
				</fieldset>
				<form class="layui-form" method="post"
					action="add?modify_book_id=<%=a.getBookid()%>">
			</div>
			<div class="layui-form layui-form-pane">
				<label class="layui-form-label">书名</label>
				<div class="layui-input-block">
					<input name="book_name" class="layui-input" type="text"
						value=<%=book_name%> name="admin_id" autocomplete="off"
						lay-verify="title">
				</div>
			</div>
			<div class="layui-form layui-form-pane">
				<label class="layui-form-label">作者</label>
				<div class="layui-input-block">
					<input name="author" class="layui-input" type="text"
						value=<%=author%> name="admin_id" autocomplete="off"
						lay-verify="title">
				</div>
			</div>
			<div class="layui-form layui-form-pane">
				<label class="layui-form-label">出版社</label>
				<div class="layui-input-block">
					<input name="publisher" class="layui-input" type="text"
						value=<%=publisher%> name="admin_id" autocomplete="off"
						lay-verify="title">
				</div>
			</div>

			<div class="layui-form layui-form-pane">
				<label class="layui-form-label">其他信息</label>
				<div class="layui-input-block">
					<input name="other_info" class="layui-input" type="text"
						value="<%=other_info%>" name="admin_id" autocomplete="off"
						lay-verify="title">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" type="submit" lay-filter="formDemo">保存</button>
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