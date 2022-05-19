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

	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 30px;">
					<legend>设置最长借阅天数</legend>
				</fieldset>
				<form class="layui-form" method="post" action="add">
			</div>
			<div class="layui-form layui-form-pane">
				<label class="layui-form-label">借阅天数</label>
				<div class="layui-col-xs4">
					<div class="layui-input-inline">
						<input name="max_keeping_days" class="layui-input" type="text"
							autocomplete="off" lay-verify="title">
					</div>
				</div>
			</div>
			<button class="layui-btn" type="submit" lay-filter="formDemo">保存</button>
			</form>
		</div>
		<div class="layui-col-md12">
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 30px;">
				<legend>是否开启黑名单</legend>
			</fieldset>
			<form class="layui-form" method="post" action="add">
				<%if(!Database.getDatabase().contain_blacklist()) {%>
				<button class="layui-btn" name="open" value="true" type="submit"
					lay-filter="formDemo">开启</button>
				<%}else{ %>
				<button class="layui-btn" name="open" value="false" type="submit"
					lay-filter="formDemo">关闭</button>
				<%} %>
			</form>
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