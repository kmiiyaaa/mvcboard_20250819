<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

방문예약하기
<hr>
<form action = "reservationOk.jsp" method = "post"><br><br>
	이름 : <input type="text" name="rname" required="required"> <br><br>  <!--  required : 필수입력 -->
	전화번호 : <input type="text" name="rphone" required="required"><br><br>
	예약일 : <input type="date" name="rdate" required="required"><br><br>
	예약시간 : <input type="time" name="rtime" required="required"><br><br>
	<input type="submit" value="예약하기">
	
</form>


</body>
</html>