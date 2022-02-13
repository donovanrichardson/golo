package com.text.golo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Keyword {

    private String name;
    private double tfIdf;

}
