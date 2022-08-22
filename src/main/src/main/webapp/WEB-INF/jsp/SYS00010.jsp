<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.yoyaku.entity.Item" %>

<%
Item item = (Item)request.getAttribute("item");
%>
<!DOCTYPE html>
<html lang="ja">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 	<title>KaigiYoyaku</title>
</head>

<body>
	<h1>管理画面</h1>
	<a href="/KYS/top">会議予約に戻る</a>
	<a href="/KYS/manage">管理に戻る</a>
	
	<form action="/KYS/manage" method="post"><br>
		<input type="hidden" name="itemNum" value=<%=item.getItemNum() %>>
		<label>変更前: <%=item.getItemName() %> : <%=item.getItemOrder() %></label><br>
		<label for="itemName">変更後: </label>
		<input type="text" name="itemName">
		<input type="text" name="itemOrder"><br>
	
		<button type="submit" name="update" value="update">送信</button>
	</form>
	
</body>
</html>