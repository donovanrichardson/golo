package com.text.golo.controller;

import com.text.golo.entity.Text;
import com.text.golo.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
//@CrossOrigin(origins="http://localhost:4200", allowedHeaders = {"X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"}, methods= {GET, POST, OPTIONS, DELETE, PUT, PATCH})
// path should be api/v1/texts
@RequestMapping("/api/v1/text")
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

//    @CrossOrigin(origins="http://localhost:4200", allowedHeaders = {"X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"}, methods= {GET, POST, OPTIONS, DELETE, PUT, PATCH})
    @PostMapping("/")
    public Text addText(@RequestBody Text text){
        return textService.addText(text);
    }

}
