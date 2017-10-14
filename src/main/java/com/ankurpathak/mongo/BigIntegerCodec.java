package com.ankurpathak.mongo;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.math.BigInteger;

/**
 * Created by ankur on 07-02-2017.
 */
public class BigIntegerCodec implements Codec<BigInteger> {
    @Override
    public BigInteger decode(BsonReader bsonReader, DecoderContext decoderContext) {
        String bigIntegerString = bsonReader.readString();
        if(bigIntegerString == null)
            return null;
        else return new BigInteger(bigIntegerString);
    }

    @Override
    public void encode(BsonWriter bsonWriter, BigInteger bigInteger, EncoderContext encoderContext) {
        if(bigInteger == null)
            bsonWriter.writeNull();
        else
            bsonWriter.writeString(bigInteger.toString());
    }

    @Override
    public Class<BigInteger> getEncoderClass() {
        return BigInteger.class;
    }
}
