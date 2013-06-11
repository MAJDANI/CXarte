package com.novedia.talentmap.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.novedia.talentmap.model.entity.JsonException;

public interface INotFoundException {
	
	@ExceptionHandler({NoSuchRequestHandlingMethodException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody JsonException handlerNotFoundException(Exception ex);

}
