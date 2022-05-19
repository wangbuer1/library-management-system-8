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
	<div style="padding: 20px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 30px;">
					<legend>读者管理</legend>
				</fieldset>
				<form class="layui-form" method="post" action="search">
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label"><i
							class="layui-icon layui-icon-search"></i>查询</label>
						<div class="layui-input-block">
							<div class="layui-col-xs10">
								<input name="_reader_id" class="layui-input" type="text"
									placeholder="请输入读者ID号或姓名" autocomplete="off" lay-verify="title">
							</div>
							<input type="submit" value="查询" class="layui-btn ">
						</div>
					</div>
				</form>
			</div>
			<%
		ArrayList<reader> a = null;
	if(session.getAttribute("result")!=null)
	{
	a = (ArrayList<reader>) session.getAttribute("result");
	for (int i = 0; i < a.size(); i++) {
	%>
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">
						<div class="layui-col-xs4">
							<i class="layui-icon layui-icon-username">读者ID:<%=a.get(i).getReader_id()%></i>
						</div>
						<div class="layui-col-xs4">
							<i class="layui-icon layui-icon-friends">姓名：<%=a.get(i).getReader_name()%></i>
						</div>
						<div class="layui-col-xs4">
							<i class="layui-icon layui-icon-cellphone-fine">电话：<%=a.get(i).getReader_tel() %></i>
						</div>
					</div>
					<div class="layui-card-body">
						密码：<%=a.get(i).getPwd()%>

						<div class="layui-col-xs-offset8">
							<a href="delete?id=<%=a.get(i).getReader_id()%>"><i
								class="layui-icon layui-icon-delete">删除用户</i></a>
						</div>
						<div class="layui-col-xs-offset8">
							<%if(Database.getDatabase().contain_blacklist()){
							if(a.get(i).getBlacklist().equals("1")){
							%>
							<a href="add?id=<%=a.get(i).getReader_id()%>&&choice=0"><i
								class="layui-icon layui-icon-delete">移出黑名单</i></a>
							<% }else{%>
							<a href="add?id=<%=a.get(i).getReader_id()%>&&choice=1"><i
								class="layui-icon layui-icon-delete">加入黑名单</i></a>
							<%}} %>
						</div>
					</div>
				</div>
			</div>

			<%} }%>
		</div>
	</div>


</body>
</html>