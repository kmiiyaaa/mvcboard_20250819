package com.kmii.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kmii.dto.BoardDto;
import com.kmii.dto.MemberDto;




public class MemberDao {
	
	// DB 커넥션 준비
	private String driverName = "com.mysql.jdbc.Driver";   // MYSQL JDBC 드라이버 이름 - mysql사용시 fix값 고정
	private String url = "jdbc:mysql://localhost:3306/jspdb";   // MYSQL이 설치된 서버의 주소(ip)와 연결할 db(스키마) 이름
	private String userName = "root";
	private String password = "12345";	
			
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	
	public int loginCheck(String mid , String mpw) {  // 로그인 성공 여부 반환 메서드 
		String sql = "SELECT * FROM members WHERE memberid =? AND memberpw=?";
		int sqlResult = 0;
	
		 try {
		    	
		        Class.forName(driverName);
		        conn = DriverManager.getConnection(url, userName, password);
		        
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, mid);
		        pstmt.setString(2, mpw);
		        rs = pstmt.executeQuery();  // 아이디와 비밀번호가 일치하는 레코드 1개 / 0개

		        if (rs.next()) { //로그인 성공
		        	sqlResult = 1;
		        } else {
		        	sqlResult = 0;
		        }
			    
		 } catch(Exception e) {
			    	System.out.println("로그인 실패");
			        e.printStackTrace();
			    
			    } finally {
			        try { if(rs!=null) 
			        	rs.close(); 
			        } catch(Exception e) {}
			        try { if(pstmt!=null) 
			        	pstmt.close(); 
			        } catch(Exception e) {}
			        try { if(conn!=null) 
			        	conn.close(); 
			        } catch(Exception e) {}
			    }
	
			    return sqlResult;
			}
		
	}

	
	

