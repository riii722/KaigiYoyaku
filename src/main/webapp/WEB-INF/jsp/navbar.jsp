<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.yoyaku.entity.MyDate"%>

<!-- データを取得 -->
<%
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
%>

<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
	<a class="fa-regular fa-calendar fa-2x navbar-brand"
		href="/YoyakuSystem/top"></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item my-nav-li"><a class="nav-link btn btn-outline-light"
				href="/YoyakuSystem/reserve?YEAR=<%=year%>&MONTH=<%=month%>&DAY=<%=day%>">予約する</a></li>
			<li class="nav-item my-nav-li"><a class="nav-link btn btn-sm btn-outline-light"
				href="/YoyakuSystem/month">月</a></li>
			<li class="nav-item my-nav-li"><a class="nav-link btn btn-sm btn-outline-light"
				href="/YoyakuSystem/week">週</a></li>
			<li class="nav-item my-nav-li"><a class="nav-link btn btn-sm btn-outline-light"
				href="/YoyakuSystem/day">日</a></li>
		</ul>
	</div>
		
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link"
			href="/YoyakuSystem/manage"><i class="fa-solid fa-gear"></i></a></li>
	</ul>
	</div>
</nav>