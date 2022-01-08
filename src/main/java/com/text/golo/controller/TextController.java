package com.text.golo.controller;

import com.text.golo.entity.Text;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// path should be api/v1/texts
public class TextController {

    @GetMapping("/")
    public List<Text> getTexts(){
        return null;
    }

    @GetMapping("/{id}")
    public Text getText(@PathVariable String id){
        return null;
    }

    @PostMapping("/")
    public Text addText(Text text){
        return null;
    }

}
