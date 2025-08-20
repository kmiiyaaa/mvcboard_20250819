<%@ page import="com.kmii.dao.BoardDao" %>
<%@ page import="com.kmii.dto.BoardDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><%="글 수정" + "새 글 작성" %></title>
<link rel="stylesheet" href="css/writecss.css">
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


<div class="board-container">
    <h2>게시글 수정</h2>

    <form action="modifyOk.do" method="post" class="edit-form">
    <input type="hidden" name="bnum" value="${boardDto.bnum }">
      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" id="title" name="btitle" required />
      </div>

      <div class="form-group">
        <label for="author">작성자</label>
        <input type="text" id="author" name="memberid"  readonly />
      </div>

      <div class="form-group">
        <label for="content">내용</label>
        <textarea id="content" name="bcontent" rows="10" required>

		</textarea>
      </div>

      <div class="form-buttons">
        <button type="submit" class="btn btn-primary">저장</button>
        <a href="javascript:history.go(-1)" class="btn btn-secondary">취소</a>
      </div>
    </form>
  </div>
</div>
<footer>© 2025 Board</footer>
</main>
</body>
</html>
