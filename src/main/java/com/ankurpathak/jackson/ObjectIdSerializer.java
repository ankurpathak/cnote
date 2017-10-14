package com.ankurpathak.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * Created by ankur on 06-03-2017.
 */
public class ObjectIdSerializer extends StdSerializer<ObjectId> {
    public ObjectIdSerializer(Class<ObjectId> classObjectId) {
        super(classObjectId);
    }

    @Override
    public void serialize(ObjectId objectId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(objectId.toHexString());
    }


    public ObjectIdSerializer(){
        this(ObjectId.class);
    }
}
