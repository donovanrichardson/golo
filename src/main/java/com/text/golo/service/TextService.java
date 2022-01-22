package com.text.golo.service;

import com.text.golo.entity.Text;
import org.springframework.beans.factory.annotation.Autowired;
import com.text.golo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextService {

    @Autowired
    TextRepository textRepository;

    public Text addText(Text text) {
        return textRepository.insert(text);
    }

    public List<Text> allTexts() {
        return textRepository.findAll();
    }

    public Text getTextById(String id) {
        Optional<Text> retrieved = textRepository.findById(id);
        return retrieved.orElse(null);
//        should throw not found error
    }
}
