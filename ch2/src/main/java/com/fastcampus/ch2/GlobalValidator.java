package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz); // clazz가 User 또는 그 자손인지 확인
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		System.out.println("GlobalValidator.validate() is called");
		
		User user = (User)target;

		String id = user.getId();
		String pwd = user.getPwd();
		
//		if(id==null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
		
//		if(id==null || id.length() == 0) {
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
//			return;
//		}
		
		if(id==null || id.length() == 0) {
			errors.rejectValue("id", "required", null);
			return;
		}
		
		if(id.length() <  5 || id.length() > 12) {
			errors.rejectValue("id", "invalidLength", new String[]{"5", "12"}, null);
			return;
		}

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		if(pwd==null || pwd.length() == 0) {
			errors.rejectValue("pwd", "required", null);
			return;
		}
		
		if(pwd==null || pwd.length() <  5 || pwd.length() > 12) {
			errors.rejectValue("pwd", "invalidLength", new String[]{"5", "12"}, null);
			return;
		}
	}

}
