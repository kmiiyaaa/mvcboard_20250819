<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <!-- 기존 CSS 유지 -->
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="login-container">
    <form action="loginOk.jsp" method="post">
        <input type="text" name="userid" placeholder="아이디" required>
        <input type="password" name="password" placeholder="비밀번호" required>
        <div class="login-links">
            <a href="#">아이디찾기</a>
            <a href="#">비밀번호찾기</a>
            <a href="register.jsp">회원가입</a>
        </div>
        <button type="submit">로그인</button>
    </form>
</div>
    </main>
</body>
</html>