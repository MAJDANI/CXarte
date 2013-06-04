package com.novedia.talentmap.rest.exception;

import java.text.ParseException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.novedia.talentmap.model.entity.JsonException;


public class TalentMapRestHandlerException {
	
	
	@ExceptionHandler({TypeMismatchException.class, ParseException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody JsonException handlerBadRequestException(Exception ex){
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.BAD_REQUEST.value());
		if (ex instanceof ParseException) {
			jsonError.setMessage("Format de date incorrect ");
		} else {
			jsonError.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		
		return jsonError;
	}
	
	
	
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody JsonException handlerNotAllowedMethodException(){
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		jsonError.setMessage(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		return jsonError;
	}
	
	
	@ExceptionHandler({NoSuchRequestHandlingMethodException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody JsonException handlerNotFoundException(){
		JsonException jsonError = new JsonException();
		jsonError.setCode(HttpStatus.NOT_FOUND.value());
		jsonError.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
		return jsonError;
	}
	
	
	

	
	

	

}
