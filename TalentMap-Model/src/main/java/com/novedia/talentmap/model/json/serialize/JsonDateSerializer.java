package com.novedia.talentmap.model.json.serialize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date> {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator,
	    SerializerProvider serializerProvider) throws IOException,
	    JsonProcessingException {

	String formattedDate = formatter.format(date);
	jsonGenerator.writeString(formattedDate);

    }

}
