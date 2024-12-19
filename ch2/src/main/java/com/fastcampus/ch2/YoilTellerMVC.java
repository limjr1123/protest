package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@Controller
public class YoilTellerMVC {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception e) {
    	e.printStackTrace();
    	return "yoilError";
    }
	
	@RequestMapping("/getYoilMVC") // http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
    //    public static void main(String[] args) {
    public String main(@RequestParam(required=true)int year
    				,@RequestParam(required=true)int month
    				,@RequestParam(required=true)int day, Model model) throws IOException {
    	
	    	if(!isValid(year, month, day))
	    		return "yoilerror";
	    	
	        char yoil = getYoil(year, month, day);
	
	        model.addAttribute("year",year);
	        model.addAttribute("month",month);
	        model.addAttribute("day",day);
	        model.addAttribute("yoil",yoil);
	        
        return "yoil";
    }

	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " 일월화수목금토".charAt(dayOfWeek);   // 일요일:1, 월요일:2, ... 
	}
}