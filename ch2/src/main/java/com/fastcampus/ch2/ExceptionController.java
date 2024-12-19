package com.fastcampus.ch2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	// 캐치블럭 역할을 함 
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String catcher(Exception ex, Model model) {
//		model.addAttribute("ex", ex);
		return "error";
	}
	
	@ExceptionHandler(NullPointerException.class) 
	public String catcher2(Exception ex, Model model) {
		model.addAttribute("ex", ex);
		return "error";
	}
	
//	@RequestMapping("ex")
//	public String main(Model model) throws Exception {
//		try {
//			throw new Exception("예외발생");
//		} catch (Exception ex) {
//			model.addAttribute("ex", ex);
//			return "error";
//		}
//	}
	
	@RequestMapping("ex")
	public String main(Model model) throws Exception {
		throw new Exception("예외발생");
	}
	
	@RequestMapping("ex2")
	public String main2() throws Exception {
		throw new NullPointerException("예외발생");
	}
}
