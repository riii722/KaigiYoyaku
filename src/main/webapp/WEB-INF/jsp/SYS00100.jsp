<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="jp.co.yoyaku.entity.Item"%>
<%@ page import="jp.co.yoyaku.entity.KaigiSyosai"%>
<%@ page import="jp.co.yoyaku.entity.MyDate"%>

<%
//データを取得
ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("item");
ArrayList<KaigiSyosai> kaigi = (ArrayList<KaigiSyosai>) request.getAttribute("kaigiSyosai");
MyDate mydate = (MyDate) request.getAttribute("mydate");
int year = mydate.getYear();
int month = mydate.getMonth();
int day = mydate.getDay();
String yobi = mydate.getYobi();
int row = 30; //時間数(8:00-22:00)30分単位
int time = 100;
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-4.6.0-dist/css/bootstrap.min.css">
<!-- Font Awesome CSS -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/fontawesome-free-6.1.2-web/css/all.min.css">
<!-- Tempus Dominus CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />
<!-- 自作CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<title>YoyakuSystem</title>
</head>




<!-- <body onload="init()"> -->
<body>
	<div class="container-fluid">
		<!-- navbar -->
		<jsp:include page="navbar.jsp">
			<jsp:param name="year" value='<%= year %>' />
			<jsp:param name="month" value='<%= month %>' />
			<jsp:param name="day" value='<%= day %>' />
		</jsp:include>
	
		<!-- Pagenation -->
		<div class="align-items-center bg-dark">
			<ul class="pagination">
				<!-- Previous -->
				<li class="page-item"><a class="page-link my-pagenation" id="pervDate"
					href="/yoyaku/top?YEAR=<%= year %>&MONTH=<%= month %>&DAY=<%= day - 1 %>" 
					aria-label="前"><span aria-hidden="true">&laquo;</span></a></li>
				<!-- Today -->
				<li class="page-item"><a class="page-link my-pagenation" id="today" 
					href="/yoyaku/top">今日</a></li>
				<!-- Next -->
				<li class="page-item"><a class="page-link my-pagenation" id="nextDate"
					href="/yoyaku/top?YEAR=<%= year %>&MONTH=<%= month %>&DAY=<%= day + 1 %>" 
					aria-label="次"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
			<!-- Date -->
			<p class="float-left my-cal2"><%= year %></p>
			<p class="float-left my-cal3">年</p>
			<p class="float-left my-cal1"><%= month %></p>
			<p class="float-left my-cal2">月</p>
			<p class="float-left my-cal1"><%= day %></p>
			<p class="float-left my-cal2">日</p>
			<p class="float-left my-cal3 circle"><%= yobi %></p>
		</div>
			
		<div class="table-responsive table-striped">
			<table id="yoyakuSyosai" class="table">
				<thead>
					<tr>
						<th scope="col"></th>
						<% for (int t = 8; t <= 22; t++) { %>
						<th scope="col"><%= t %>:00</th>
						<th scope="col"></th>
						<% } %>
					</tr>
				</thead>
				<tbody>
					<% for (int r = 0; r < items.size(); r++) { %>
					<% int tmp = 0; %>
					<tr>
						<!-- 縦軸タイトル -->
						<td><%=items.get(r).getItemName()%></td>
	
						<!-- 予約内容 -->
						<% for (int i = 0; i < kaigi.size(); i++) { %>
							<% int cnt = 0; %>
							<% if (kaigi.get(i).getItemName().equals(items.get(r).getItemName())) { %>
							<% for (int t = 8; t <= 22; t++) { %>
								<!-- xx:00台 -->
								<% if (Integer.parseInt(kaigi.get(i).getStartTime()) == (t * 100)) { %>
									<% for (int n = 0; n < (cnt - tmp); n++) { %>
									<td></td>
									<% } %>
	
						<!-- colspanの決定 -->
						<!-- （終了時間－開始時間）を100で割った場合、
						　　┗余りがない場合(xx:00)は、（終了時間－開始時間）を '100'で割った数＊２ をcolspanに設定する 
						　　┗余りがある場合(xx:30)は、（終了時間－開始時間）を '100'で割った数 をcolspanに設定する -->
						<%
						int cal = (Integer.parseInt(kaigi.get(i).getEndTime())) - (Integer.parseInt(kaigi.get(i).getStartTime()));
						int amari = (cal % time);
						int waru = (cal / time);
						%>
						<% if (amari == 0) { %>
							<td colspan=<%= waru * 2 %>>
								<%-- <a href="/yoyaku/detail?YoyakuNum=<%=kaigi.get(i).getYoyakuNum() %>')"><%=kaigi.get(i).getKaigiName() %> <%=kaigi.get(i).getStartTime() %>~<%=kaigi.get(i).getEndTime() %></a> --%>
								<button type="button" class="btn btn-info btn-block"
									onclick="openWindow('/yoyaku/detail?YoyakuNum=<%=kaigi.get(i).getYoyakuNum()%>')"><%=kaigi.get(i).getKaigiName()%>
									<%=kaigi.get(i).getStartTime()%>~<%=kaigi.get(i).getEndTime()%></button>
							</td>
							<% tmp = cnt + 2; %>
						<% } else { %>
							<td colspan=<%= waru %>>
								<button type="button" class="btn btn-info btn-block"
									onclick="openWindow('/yoyaku/detail?YoyakuNum=<%=kaigi.get(i).getYoyakuNum()%>')"><%=kaigi.get(i).getKaigiName()%>
									<%=kaigi.get(i).getStartTime()%>~<%=kaigi.get(i).getEndTime()%></button>
							</td>
							<% tmp = cnt + 1;%>
						<% } %>
						<% } else { %>
						<% cnt = cnt + 1; %>
						<% } %>
	
						<!-- xx:30台 -->
						<%
						if (Integer.parseInt(kaigi.get(i).getStartTime()) == (t * 100 + 30)) {
						%>
						<%
						for (int n = 0; n < (cnt - tmp); n++) {
						%>
						<td></td>
						<%
						}
						%>
	
						<!-- colspanの決定 -->
						<%
						int cal = (Integer.parseInt(kaigi.get(i).getEndTime())) - (Integer.parseInt(kaigi.get(i).getStartTime()));
						%>
						<%
						int amari = (cal % time);
						%>
						<%
						int waru = (cal / time);
						%>
						<%
						if (amari == 0) {
						%>
						<td colspan=<%=waru * 2%>>
							<button type="button" class="btn btn-info btn-block"
								onclick="openWindow('/yoyaku/detail?YoyakuNum=<%=kaigi.get(i).getYoyakuNum()%>')"><%=kaigi.get(i).getKaigiName()%>
								<%=kaigi.get(i).getStartTime()%>~<%=kaigi.get(i).getEndTime()%></button>
						</td>
						<%
						tmp = cnt + 2;
						%>
						<%
						} else {
						%>
						<td colspan=<%=waru%>>
							<button type="button" class="btn btn-info btn-block"
								onclick="openWindow('/yoyaku/detail?YoyakuNum=<%=kaigi.get(i).getYoyakuNum()%>')"><%=kaigi.get(i).getKaigiName()%>
								<%=kaigi.get(i).getStartTime()%>~<%=kaigi.get(i).getEndTime()%></button>
						</td>
						<%
						tmp = cnt + 1;
						%>
						<%
						}
						%>
	
						<%
						} else {
						%>
						<%
						cnt = cnt + 1;
						%>
						<%
						}
						%>
	
						<%
						}
						%>
	
						<%
						}
						%>
	
						<%
						}
						%>
	
						<!-- <td>が足りない所を足す -->
						<%
						for (int n = 0; n < (row - tmp); n++) {
						%>
						<td></td>
						<%
						}
						%>
	
					</tr>
					<%
					}
					%>
	
				</tbody>
			</table>
		</div>
		
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/bootstrap-4.6.0-dist/css/bootstrap.min.js"></script>
	<!-- Moment.js -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/locale/ja.js"></script>
	<!-- Font Awesome -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/js/all.min.js"
		integrity="sha256-MAgcygDRahs+F/Nk5Vz387whB4kSK9NXlDN3w58LLq0="
		crossorigin="anonymous"></script>

	<!--自作JS -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/script/common.js"></script>

</body>
</html>