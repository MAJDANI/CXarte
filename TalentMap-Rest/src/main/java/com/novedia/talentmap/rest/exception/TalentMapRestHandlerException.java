package com.novedia.talentmap.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.novedia.talentmap.model.entity.JsonException;


public class TalentMapRestHandlerException {
	

	/**
	 * Allow to handle Not Allowed method exception
	 * @param ex ex exception was thrown
	 * @return Jsonexception objet
	 */
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody JsonException handlerNotAllowedMethodException(Exception ex){
		System.out.println("yyyyyyyy");
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		jsonError.setMessage(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		return jsonError;
	}
	
}
