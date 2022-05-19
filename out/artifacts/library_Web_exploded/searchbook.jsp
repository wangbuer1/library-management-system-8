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
	<%
		if (Database.getDatabase().is_punished((String) session.getAttribute("id"))
			|| Database.getDatabase().is_full((String) session.getAttribute("id")))
		session.setAttribute("is_punished", "true");
	%>
	<script src="./layui/layui.js"></script>
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 30px;">
					<legend>查询书籍</legend>
				</fieldset>
				<form class="layui-form" method="post" action="search">
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label"><i
							class="layui-icon layui-icon-search"></i>查询</label>
						<div class="layui-input-block">
							<div class="layui-col-xs10">
								<input name="book_id_name" class="layui-input" type="text"
									placeholder="请输入查询信息" autocomplete="off" lay-verify="title">
							</div>
							<input type="submit" value="查询" class="layui-btn ">
						</div>
					</div>
				</form>
			</div>
			<%
				ArrayList<book> a = null;
			if (session.getAttribute("book") != null) {
				a = (ArrayList<book>) session.getAttribute("book");
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
						if (!a.get(i).getOther_info().equals(""))
							out.print("," + a.get(i).getOther_info());
						out.print("<br><br>");

						if (a.get(i).getState().equals("在库") && session.getAttribute("identity").equals("reader")
								&& session.getAttribute("is_punished").equals(false)) {
						%>
						<div class="layui-col-xs-offset8">
							<a href="add?book_id=<%=a.get(i).getBookid()%>"> <i
								class="layui-icon layui-icon-face-smile">借阅本书</i></a>
						</div>
						<%
							}
						%>
						<%
							if (session.getAttribute("identity").equals("admin")) {
						%>
						<a href="search?search_book_id=<%=a.get(i).getBookid()%>"> <i
							class="layui-icon layui-icon-edit">修改信息</i></a>
						<%
							}
						%>
						<%
							if (a.get(i).getState().equals("在库") && session.getAttribute("identity").equals("admin")) {
						%>
						<div class="layui-col-xs8">
							<a href="delete?delete_book_id=<%=a.get(i).getBookid()%>"> <i
								class="layui-icon layui-icon-delete">删除本书</i></a>
						</div>
						<%
							}
						%>


					</div>
				</div>
			</div>

			<%
				}
			}
			%>
		</div>
	</div>


</body>
</html>