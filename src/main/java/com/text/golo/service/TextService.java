package com.text.golo.service;

import com.text.golo.entity.Text;
import org.springframework.beans.factory.annotation.Autowired;
import com.text.golo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextService {

//    @Autowired
    TextRepository textRepository;

    public Text addText(Text text) {
        return null;
    }

    public List<Text> allTexts() {
        return null;
    }

    public Text getTextById(String id) {
        return null;
    }
}
