package com.example.notesapi.config;

import com.example.notesapi.dto.NoteDto;
import com.example.notesapi.entity.Note;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Note.class, NoteDto.class).setPreConverter(context ->
        {
            NoteDto noteDto = context.getDestination();
            noteDto.setAmountOflikes(context.getSource().getLikes().size());
            return noteDto;
        });
        return modelMapper;
    }

//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        MongoTemplate template = new MongoTemplate(mongoClient(), database);
//        MappingMongoConverter mmc = (MappingMongoConverter)template.getConverter();
//        mmc.setCustomConversions( new MongoCustomConversions(asList(
//                new MongoConverters.BinaryToUuidConverter(),
//                new MongoConverters.UuidToBinaryConverter()
//        )));
//        mmc.afterPropertiesSet();
//        return template;
//    }

}
