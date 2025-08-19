<%@ page import="com.kmii.dao.BoardDao" %>
<%@ page import="com.kmii.dto.BoardDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>Board Detail</title>
  <link rel="stylesheet" href="css/boardStyle.css">
</head>
<body>
 <!-- 상단 네비 -->
<div class="nav">
  <div class="nav-inner container">
    <div class="brand">
      <a href="homepage.jsp" style="color:inherit; text-decoration:none;">HOME</a>
    </div>
    <div>
      <a href="#" style="color:var(--accent); text-decoration:none">로그인</a>
</div>
      <div><a href="#" style="color:var(--accent); text-decoration:none">로그인</a></div>
    </div>
  </div>

  <main class="container">
    <h1>게시글 상세보기</h1>
    <div class="panel">
         <!-- 상세보기 카드 -->
                  <div class="table">
                    <div class="thead">
                      <div style="flex:1;">제목</div>
                      <div style="flex:1;">작성자</div>
                      <div style="flex:1;">작성일</div>
                      <div style="flex:0.5;">조회</div>
                    </div>

                  ${boardDto.bnum}
                  ${boardDto.btitle}
                  ${boardDto.bcontent}
                  ${boardDto.memberid}
                  ${boardDto.bhit}
                  
     
    </div>

    <footer>© 2025 Board</footer>
  </main>
</body>
</html>
