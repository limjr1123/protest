package com.fastcampus.ch2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
class MyException extends RuntimeException {
	MyException(String msg) {
		super(msg);
	}
	MyException() {
		this("");
	}
}

@Controller
public class ExceptionController2 {
	
	@RequestMapping("ex3")
	public String main(Model model) throws Exception {
		throw new MyException("예외발생");
	}
	
	@RequestMapping("ex4")
	public String main2() throws Exception {
		throw new NullPointerException("예외발생");
	}
}
