package com.text.golo.service;

import com.text.golo.entity.Text;
import com.text.golo.repository.TextRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TextServiceTest {

    @Mock
    TextRepository textRepository;

    @InjectMocks
    TextService textService;

    private final Text TEXT_A = Text.builder().content("A").build();
    private final Text TEXT_B = Text.builder().content("B").build();

    private final Text INSERTED_TEXT = Text.builder().id("A").build();

    private final List<Text> TEXT_LIST = Arrays.asList(TEXT_A,TEXT_B);

    @Test
    void addText() {

        when(textRepository.insert(TEXT_A)).thenReturn(INSERTED_TEXT);
        Text returnedText = textService.addText(TEXT_A);
        verify(textRepository).insert(TEXT_A);
        assertEquals(INSERTED_TEXT,returnedText);

    }

    @Test
    void allTexts() {
        when(textRepository.findAll()).thenReturn(TEXT_LIST);
        List<Text> allTexts = textService.allTexts();
        verify(textRepository).findAll();
        assertEquals(TEXT_LIST,allTexts);
    }

    @Test
    void getTextById() {

        when(textRepository.findById("2")).thenReturn(Optional.of(TEXT_B));
        when(textRepository.findById("3")).thenReturn(Optional.empty());

        Text queried2 = textService.getTextById("2");
        Text queried3 = textService.getTextById("3");

        verify(textRepository).findById("2");
        verify(textRepository).findById("3");

        assertEquals(TEXT_B, queried2);
        assertNull(queried3);


    }

    @Test
    void mostRecent10() {
//        Pageable pageable = PageRequest.of(0,10, Sort.by("timestamp").descending());
//        List<Text> mostRecent10 = textService.mostRecent10();
//        verify(textRepository).findAll(eq(pageable));
        // TODO i don't know how to test this
    }
}