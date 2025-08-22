package com.kmii.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BCommand {
	
	public void excute(HttpServletRequest request, HttpServletResponse response) ;  // 인터페이스는 추상메서드!
	

}
