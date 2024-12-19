package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
				
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//1. 세션 종료
		session.invalidate();
		//2. 홈으로 이동
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@CookieValue("id") String cookieId, String id,String pwd, boolean rememberId, String toURL
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1. id, pwd 확인 
		if(!loginChek(id,pwd)) {
			//2-1. 일치하지 않으면 loginForm으로 이동
			String msg = URLEncoder.encode("id또는 pwd가 일치하지 않습니다.", "utf-8");
			
			return "redirect:/login/login?msg="+msg;
		}
		//2-2. id와 PWD일치하면,
		//	   세션 객체에 ID 저장
		HttpSession session = request.getSession();
		session.setAttribute("id",id);
		if(rememberId) {// id 기억 체크 한 경우
			//	1. 쿠키생성
			Cookie cookie = new Cookie("id", id);
			//	2. 응답에 저장
			response.addCookie(cookie);
		} else {// id 기억 체크 해제 한 경우
			//	1. 쿠키삭제
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0);
			//	2. 응답에 저장
			response.addCookie(cookie);
		}
			
		//	3. 홈으로 이동
		toURL = toURL==null || toURL.equals("") ? "/" : toURL;
		return "redirect:" + toURL;
		
				
	}

	private boolean loginChek(String id, String pwd) {
		// TODO Auto-generated method stub
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
