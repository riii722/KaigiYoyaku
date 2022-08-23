<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.yoyaku.entity.MyDate" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="jp.co.yoyaku.entity.Item" %>

<!-- データを取得 -->
<% 
MyDate mydate = (MyDate)request.getAttribute("mydate"); 
ArrayList<Item> items = (ArrayList<Item>)request.getAttribute("item");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 自作CSS読み込み -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
	<!-- BootstrapのCSS読み込み -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<!-- Font AwesomeのCSS読み込み -->
	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<!-- Tempus DominusのCSS読み込み -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />
	<title>YoyakuSystem</title>
</head>
<body>
	<!-- navbarの読み込み -->
	<jsp:include page="navbar.jsp" />

	<div class="container">
		<hr class="my-4">
	
		<form action="/KYS/top" method="post" class="form-horizontal" name="yoyakuForm" >
			<div class="form-group row">
				<label for="yoyakuDay" class="col-sm-2 col-form-label">予約日</label>
	   			<div class="col-sm-7">
					<div class="input-group date" id="datepicker" data-target-input="nearest">
						<input type="text" name="yoyakuDay" class="form-control datetimepicker-input" data-target="#datepicker" data-toggle="datetimepicker" 
							VALUE=<%=mydate.getDate() %>  />
						<div class="input-group-append" data-target="#datepicker" data-toggle="datetimepicker">
							<div class="input-group-text"><i class="fa fa-calendar"></i></div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="time" class="col-sm-2 col-form-label">時間</label>
	   			<div class="col-sm-3">
		            <div class="input-group date" id="startTime" data-target-input="nearest">
		            	<input type="text" name="startTime" class="form-control datetimepicker-input" data-target="#startTime" data-toggle="datetimepicker" />
		            	<div class="input-group-append" data-target="#startTime" data-toggle="datetimepicker">
		            		<div class="input-group-text"><i class="fa fa-clock"></i></div>
		            	</div>
		            </div>
		        </div>
				<label for="kara" class="col-form-label"> ～ </label>
	   			<div class="col-sm-3">
		            <div class="input-group date"  id="endTime" data-target-input="nearest">
		            	<input type="text" name="endTime" class="form-control datetimepicker-input" data-target="#endTime" data-toggle="datetimepicker" />
		            	<div class="input-group-append" data-target="#endTime" data-toggle="datetimepicker">
		            		<div class="input-group-text"><i class="fa fa-clock"></i></div>
		            	</div>
		            </div>
		        </div>
			</div>
			
			<fieldset class="form-group">
				<div class="row">
		      		<legend class="col-form-label col-sm-2 pt-0">会議場所</legend>
		   			<div class="col-sm-7">
						<%for(int i = 0; i < items.size(); i++){ %>
	      					<div class="form-check  form-check-inline">
								<input class="form-check-input" type="radio" name="itemNum"  id="kaigiSpace" value=<%=items.get(i).getItemNum() %>>
								<label class="form-check-label" for="kaigiSpace"><%=items.get(i).getItemName() %></label>
							</div>
						<%} %>
				</div>
			</fieldset>
			
			<div class="form-group row">
				<label for="kaigName" class="col-sm-2 col-form-label">会議名</label>
	   			<div class="col-sm-7">
					<input type="text" id="kaigiName" class="form-control" name="kaigiName" maxLength="50" value=""  />
				</div>
			</div>
			
			<div class="form-group row">
				<label for="yoyakuName" class="col-sm-2 col-form-label">予約者名</label>
	   			<div class="col-sm-7">
					<input type="text" id="yoyakuName" class="form-control" name="yoyakuName" maxLength="10" value=""  />
				</div>
			</div>
			
	        <hr class="my-4">
			
	       	<button type="submit" class="btn btn-outline-primary btn-block"  onClick="return check();" >予約する</button>
		</form>
	</div>


<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<!-- Moment.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js" integrity="sha256-4iQZ6BVL4qNKlQ27TExEhBN1HFPvAvAMbFavKKosSWQ=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment-with-locales.min.js" integrity="sha256-AdQN98MVZs44Eq2yTwtoKufhnU+uZ7v2kXnD5vqzZVo=" crossorigin="anonymous"></script>

<!-- Tempus Dominus Script -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Font Awesome -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/js/all.min.js" integrity="sha256-MAgcygDRahs+F/Nk5Vz387whB4kSK9NXlDN3w58LLq0=" crossorigin="anonymous"></script>

<!-- datetimepicker実装 -->
<script type="text/javascript">
	$(function () {
	    $('#datepicker').datetimepicker({
		    locale: 'ja', 
		    dayViewHeaderFormat: 'YYYY年M月' ,
		    format: 'L' ,
		    format: 'YYYY/MM/DD'
	    });
	    $('#startTime').datetimepicker({
            format: 'LT',
            format: 'HH:mm',
            stepping: 15
        });
        $('#endTime').datetimepicker({
            format: 'LT',
            format: 'HH:mm',
            stepping: 15
        });
	});
</script>

<!--自作JSの読み込み -->
<script  type="text/javascript" src="<%=request.getContextPath() %>/script/common.js"></script>

</body>
</html>