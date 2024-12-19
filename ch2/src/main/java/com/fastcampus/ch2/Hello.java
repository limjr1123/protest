package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Hello {
	
	@RequestMapping("/test")
	public void main(int a) {
		boolean yoil = isValid(a);

		System.out.println(yoil);
		System.out.println("Hello");
	}
	
	private boolean isValid(int a) {
		// TODO Auto-generated method stub
		return true;
	}
}
