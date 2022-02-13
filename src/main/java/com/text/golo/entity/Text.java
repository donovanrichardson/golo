package com.text.golo.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document
@Data
@Builder
public class Text {

    @Id
    private String id;
    String content;
    ZonedDateTime timestamp;
    List<Keyword> keywords;

}
