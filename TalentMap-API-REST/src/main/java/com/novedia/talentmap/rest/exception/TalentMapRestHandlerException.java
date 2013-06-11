package com.novedia.talentmap.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.novedia.talentmap.model.entity.JsonException;


public class TalentMapRestHandlerException {
	

	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody JsonException handlerNotAllowedMethodException(Exception ex){
		System.out.println("yyyyyyyy");
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		jsonError.setMessage(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		return jsonError;
	}
	
	
	@ExceptionHandler({NoSuchRequestHandlingMethodException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody JsonException handlerNotFoundException(NoSuchRequestHandlingMethodException ex){
		System.out.println("404");
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.NOT_FOUND.value());
		jsonError.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
		return jsonError;
	}
	
	
	

	
	

	

}
