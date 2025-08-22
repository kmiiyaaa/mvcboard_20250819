package com.kmii.command;

import java.io.IOException;

import com.kmii.dao.BoardDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BwriteCommand implements BCommand{
	
	
	public void excute(HttpServletRequest request, HttpServletResponse response) {
	
	BoardDao boardDao = new BoardDao();
	
	
	String btitle =	request.getParameter("btitle");
	String memberid =request.getParameter("memberid");
	String bcontent =request.getParameter("bcontent");
	
	boardDao.insertBoard(btitle, memberid, bcontent);
	
	
	}
	
}
