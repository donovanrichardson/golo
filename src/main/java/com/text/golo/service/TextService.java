package com.text.golo.service;

import com.text.golo.entity.Text;
import org.springframework.beans.factory.annotation.Autowired;
import com.text.golo.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextService {

    @Autowired
    TextRepository textRepository;

    @Autowired
    TfIdfService tfIdfService;

    public Text addText(Text text) {
        long count = textRepository.count();
        int sample = (int) Math.floor(Math.sqrt((double) count));
        List<Text> sampleTexts = textRepository.findAll(PageRequest.of(0,sample, Sort.by("timestamp").descending())).getContent();
        Text textToBeAdded = tfIdfService.injectKeywords(sampleTexts,text);

        return textRepository.insert(textToBeAdded);
    }

    public List<Text> allTexts() {
        return textRepository.findAll();
    }

    public Text getTextById(String id) {
        Optional<Text> retrieved = textRepository.findById(id);
        return retrieved.orElse(null);
//        should throw not found error
    }

    public List<Text> mostRecent10() {
        Page<Text> mostRecentPage = textRepository.findAll(PageRequest.of(0,10, Sort.by("timestamp").descending()));
        return mostRecentPage.getContent();
    }
}
