<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,DB.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./layui/css/layui.css">
</head>
<body bgcolor=#F2F2F2>
	<script src="./layui/layui.js"></script>
	<%
		if (Database.getDatabase().is_punished((String) session.getAttribute("id")))
		session.setAttribute("is_punished", true);
	%>
	<div style="padding: 20px; background-color: #F2F2F2;">
		<%
			if (session.getAttribute("is_punished").equals(true)) {
		%>

		<script src="./layui/layui.js"></script>
		<script>
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg("未缴纳超期图书罚款或已达到最大借阅量！！！");
			});
		</script>
		<%
			} else {
		%>
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 30px;">
					<legend>归还图书</legend>
				</fieldset>
				<form class="layui-form" method="get" action="delete">
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label"><i
							class="layui-icon layui-icon-search"></i>输入书号</label>
						<div class="layui-input-block">
							<div class="layui-col-xs10">
								<input name="book_id" class="layui-input" type="text"
									placeholder="请输入书号" autocomplete="off" lay-verify="title">
							</div>
							<input type="submit" value="归还" class="layui-btn ">
						</div>
					</div>
				</form>
			</div>
			<%
				ArrayList<book> a = null;
			if (session.getAttribute("borrowed_book") != null) {
				a = (ArrayList<book>) session.getAttribute("borrowed_book");
				for (int i = 0; i < a.size(); i++) {
			%>
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">
						<div class="layui-col-xs4">
							<i class="layui-icon layui-icon-username">书籍ID:<%=a.get(i).getBookid()%></i>
						</div>

						<div class="layui-col-xs-offset8">
							<i class="layui-icon layui-icon-date">状态：<%=a.get(i).getState()%></i>
						</div>
					</div>
					<div class="layui-card-body">
						<%
							out.print(a.get(i).getBook_name() + ",");
						out.print(a.get(i).getAuthor() + ",");
						out.print(a.get(i).getPublisher());
						%>
						<div class="layui-col-xs-offset8">
							<a href="delete?book_id=<%=a.get(i).getBookid()%>"> <i
								class="layui-icon layui-icon-face-smile">归还本书</i></a>
						</div>


					</div>
				</div>
			</div>

			<%
				}
			}
			}
			%>
		</div>
	</div>


</body>
</html>