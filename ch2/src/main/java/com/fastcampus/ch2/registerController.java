package com.fastcampus.ch2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //ctrl + shift + o 
@RequestMapping("/register")
public class registerController {
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();
		
		// 날짜 변환 형식 지정 - 작성된 컨트롤러(registerController) 내에서만 적용됨
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));
		
		binder.registerCustomEditor(String[].class, "hobby" , new StringArrayPropertyEditor("#"));
//		binder.setValidator(new UserValidator()); //UserValidator(직접 생성한)를 WebDataBinder의 로컬 validator로 등록
//		binder.addValidators(new UserValidator()); //글로벌 Validator에 추가하는 경우
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList="+validatorList);
	}
	
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String register() {
		return "registerForm";
	}

//	@RequestMapping(value = "/register/save", method=RequestMethod.POST)
	@PostMapping("/save")
	public String save(@Valid User user, BindingResult result, Model model) throws Exception {
		System.out.println(result);
		System.out.println(user);
		
		//1. 유효성 검증(수동 유효성 검증 - Validator를 직접 생성하고, validate()를 직접 호출함
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); //BindingResult는 errors의 자손
		
		// User객체 검증 결과 에러가 있으면, registerForm에서 에러내용을 표시함
		if(result.hasErrors()) {
			return "registerForm";
		}
		
		// 유효성 검증
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id�� �߸� �Է��Ͽ����ϴ�.", "UTF-8");
//			
//			model.addAttribute("msg",msg);
//			return "forward:/register/add";
//			return "redirect:/register/add?msg="+msg;
//		}
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
