<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jp.co.yoyaku.entity.Item" %>

<%
//データを取得
ArrayList<Item> items = (ArrayList<Item>)request.getAttribute("item");
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
	
	<form action="/KYS/manage" method="post" >
		<label>アイテム名: </label>
		<input type="text" name="itemName"><br>
		<label>表示順序: </label>
		<input type="text" name="itemOrder"><br>
		<input type="submit" name="add" value="add">
	</form>
	
	<table border="1" style="border-collapse: collapse">
		<thead>
			<tr>
				<th>No.</th>
				<th>アイテム名</th>
				<th>表示順序</th>
				<th colspan="2">実行</th>
			</tr>
		</thead>
		<tbody>
			<%for(int i = 0; i < items.size(); i++){ %>
				<tr>
					<td><%=(i+1) %></td>
					<td><%=items.get(i).getItemName() %></td>
					<td><%=items.get(i).getItemOrder() %></td>
					<td><a href="/KYS/manage/edit?ItemNum=<%=items.get(i).getItemNum()%>">編集</a></td>
					<td><a href="/KYS/manage/delete?ItemNum=<%=items.get(i).getItemNum()%>">削除</a></td>
				</tr>
			<%} %>
		</tbody>
	</table>

</body>
</html>