<%@page import="java.util.List"%>
<%@page import="com.kmii.dao.BoardDao"%>
<%@page import="com.kmii.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>Board</title>
  <link rel="stylesheet" href="css/boardStyle.css">
  <style>
    table { width:100%; border-collapse:collapse; }
    th, td { border:1px solid #444; padding:0.5rem; text-align:center; }
    th { background:#222; color:#eee; }
    td.title a { text-decoration:none; color:inherit; }
  </style>
</head>
<body>
<div class="nav">
  <div class="nav-inner container">
    <div class="brand"><a href="homepage.jsp" style="color:inherit; text-decoration:none;">HOME</a></div>
    <div><a href="#" style="color:black; text-decoration:none">로그인</a></div>
  </div>
</div>

<main class="container">
  <h1>자유 게시판</h1>
   <span stye="color:rosybrown;">
   	<c:if test="${not empty sessionScope.sid }">
   		<b>${sessionScope.sid}</b>님 로그인중 
   	</c:if>
   </span>
  <div style="text-align:right; margin-bottom:1rem;">
 
    <a href="insert.do"><button class="btn">글쓰기</button></a>
  </div>

  <div class="panel">
    <table>
      <thead>
        <tr>
          <th>No</th>
          <th>제목</th>
          <th>작성자</th>
          <th>이메일</th>
          <th>작성일</th>
          <th>조회</th>
        </tr>
      </thead>
        <tbody>
        <c:forEach items="${bDtos}" var="bDto">
        <tr>
          <td>${bDto.bnum }</td> 
          <td>
          <c:choose>
          	<c:when test="${fn:length(bDto.btitle) > 35}">
          		<a href="boardContent.do?bnum=${bDto.bnum}">${fn:substring(bDto.btitle, 0, 35)}...</a>
          	</c:when>
          	<c:otherwise>
          		<a href="boardContent.do?bnum=${bDto.bnum}">${bDto.btitle}</a>
          	</c:otherwise>
          </c:choose>
          </td>
          <td>${bDto.memberid }</td>
          <td>${bDto.memberDto.memberemail}</td>
          <td>${fn:substring(bDto.bdate,0,10)}</td>
          <td>${bDto.bhit }</td>
        </tr>
        </c:forEach>
        <!-- 추가 게시글 -->
      </tbody>
    </table>
  </div>
</body>
</html>

  <footer style="margin-top:1rem;">© 2025 Board</footer>
</main>
</body>
</html>