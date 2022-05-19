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
					<legend>查询违规记录</legend>
				</fieldset>
				<%
					if (session.getAttribute("identity").equals("admin")) {
				%>
				<form class="layui-form" method="post" action="search">
					<div class="layui-form layui-form-pane">
						<label class="layui-form-label"><i
							class="layui-icon layui-icon-search"></i>查询</label>
						<div class="layui-input-block">
							<div class="layui-col-xs10">
								<input name="reader_id" class="layui-input" type="text"
									placeholder="请输入读者ID号" autocomplete="off" lay-verify="title">
							</div>
							<input type="submit" value="查询" class="layui-btn ">
						</div>
					</div>
				</form>
				<%
					}
				%>
			</div>
			<%
				ArrayList<punish_record> a = null;
			if (session.getAttribute("punishment") != null) {
				a = (ArrayList<punish_record>) session.getAttribute("punishment");
				for (int i = 0; i < a.size(); i++) {
			%>
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">
						<div class="layui-col-xs4">
							<i class="layui-icon layui-icon-username">读者ID:<%=a.get(i).reader_id%></i>
						</div>
						<div class="layui-col-xs4">
							<i class="layui-icon layui-icon-date">借出日期：<%=a.get(i).start_date%></i>
						</div>
						<div class="layui-col-xs4">
							<i class="layui-icon layui-icon-date">归还日期：<%=a.get(i).end_date%></i>
						</div>
					</div>
					<div class="layui-card-body">
						<%
							out.print(a.get(i).bookid + ",");
						out.print(a.get(i).book_name + ",");
						out.print(a.get(i).author + ",");
						out.print(a.get(i).publisher);
						if (a.get(i).getExtra_days() > 0) {
						%>
						<div class="layui-col-xs-offset8">
							<i class="layui-icon layui-icon-face-cry">超期天数:<%=a.get(i).getExtra_days()%>天
							</i>
						</div>
						<%
							} else {
							if (session.getAttribute("identity").equals("admin")) {
						%>

						<div class="layui-col-xs-offset8">
							<a
								href="search?punish_record_id=<%=a.get(i).getPunish_record_id()%>"><i
								class="layui-icon layui-icon-face-smile">确认缴款</i></a>
						</div>
						<%
							}

						else {
						%>



						<div class="layui-col-xs-offset8">
							<i class="layui-icon layui-icon-face-cry">尚未缴款</i>
						</div>
						<%
							}
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