<%@ page import="com.kmii.dao.BoardDao" %>
<%@ page import="com.kmii.dto.BoardDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
	BoardDto boardDto = (BoardDto) request.getAttribute("boardDto");
	boolean isEdit = (boardDto != null);

	if(request.getParameter("error") != null) {
    out.println("<script>alert('수정 또는 삭제 권한이 없는 글입니다.');history.go(-1);</script>");
    return;  // 페이지 실행 중단
}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><%= isEdit ? "글 수정" : "글 수정" %></title>
<link rel="stylesheet" href="css/writecss.css">
</head>
<body>
<div class="nav">
    <div class="nav-inner">
        <div class="brand">
            <a href="homepage.jsp">HOME</a>
        </div>
        <div class="menu">
            <a href="boardList.do">자유게시판</a> | 
            <a href="login.jsp">로그인</a>
        </div>
    </div>
</div>

<div class="board-container">
    <h2><%= isEdit ? "게시글 수정" : "게시글 수정" %></h2>

    <form action="<%= isEdit ? "modifyOk.do" : "writeOk.do" %>" method="post" class="edit-form">
        <% if(isEdit) { %>
            <!-- 수정 시 bnum hidden으로 전달 -->
            <input type="hidden" name="bnum" value="<%= boardDto.getBnum() %>">
        <% } %>

        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" id="title" name="btitle" value="<%= isEdit ? boardDto.getBtitle() : "" %>" required />
        </div>

        <div class="form-group">
            <label for="author">작성자</label>
            <input type="text" id="author" name="memberid" value="<%= isEdit ? boardDto.getMemberid() : "" %>" readonly />
        </div>

        <div class="form-group">
            <label for="content">내용</label>
            <textarea id="content" name="bcontent" rows="10" required><%= isEdit ? boardDto.getBcontent() : "" %></textarea>
        </div>

        <div class="form-buttons">
            <button type="submit" class="btn btn-primary"><%= isEdit ? "수정 저장" : "등록" %></button>
            <a href="javascript:history.go(-1)" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>

<footer>© 2025 Board</footer>
</body>
</html>
