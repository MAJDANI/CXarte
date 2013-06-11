package com.novedia.talentmap.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.novedia.talentmap.model.entity.JsonException;

public interface IBadRequestException {
	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody JsonException handlerBadRequestException(Exception ex);

}
