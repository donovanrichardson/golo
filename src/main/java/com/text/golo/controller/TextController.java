package com.text.golo.controller;

import com.text.golo.entity.Text;
import com.text.golo.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// path should be api/v1/texts
@RequestMapping("/api/vi/text")
public class TextController {

    @Autowired
    private TextService textService;

    @GetMapping("/")
    public List<Text> getTexts(){
        return textService.allTexts();
    }

    @GetMapping("/{id}")
    public Text getText(@PathVariable String id){
        return textService.getTextById(id);
    }

    @PostMapping("/")
    public Text addText(@RequestBody Text text){
        return textService.addText(text);
    }

}
