package com.text.golo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfiguration {

//    @Bean
//    public MongoCustomConversions customConversions(){
//        List<Converter<?,?>> converters = new ArrayList<>();
//        converters.add(DateToZonedDateTimeConverter.INSTANCE);
//        converters.add( ZonedDateTimeToDateConverter.INSTANCE);
//        return new MongoCustomConversions(converters);
//    }
//
//    enum DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
//
//        INSTANCE;
//
//        @Override
//        public ZonedDateTime convert(Date source) {
//            return ofInstant(source.toInstant(), systemDefault());
//        }
//    }
//
//    enum ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
//
//        INSTANCE;
//
//        @Override
//        public Date convert(ZonedDateTime source) {
//            return Date.from(source.toInstant());
//        }
//    }

    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(LDTToZonedDateTimeConverter.INSTANCE);
        converters.add( ZonedDateTimeToLDTConverter.INSTANCE);
        return new MongoCustomConversions(converters);
    }

    @ReadingConverter
    enum LDTToZonedDateTimeConverter implements Converter<LocalDateTime, ZonedDateTime> {

        INSTANCE;

        @Override
        public ZonedDateTime convert(LocalDateTime source) {
            return ZonedDateTime.of(source, ZoneOffset.UTC);
        }
    }

    @WritingConverter
    enum ZonedDateTimeToLDTConverter implements Converter<ZonedDateTime, LocalDateTime> {

        INSTANCE;

        @Override
        public LocalDateTime convert(ZonedDateTime source) {
            return source.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        }
    }

}
