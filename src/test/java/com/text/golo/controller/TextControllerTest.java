package com.text.golo.controller;

import com.text.golo.entity.Text;
import com.text.golo.service.TextService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TextControllerTest {

    @Mock
    TextService textService;

    @InjectMocks
    TextController textController;

    private final Text T1 = Text.builder().content("1").build();
    private final Text T2 = Text.builder().content("2").build();
    private final Text T3 = Text.builder().content("3").build();
    private final Text INSERTED_TEXT = Text.builder().id("inserted").build();

    private final List<Text> TEXT_LIST = Arrays.asList(T1,T2,T3);

    @Test
    void getTexts() {
        when(textService.allTexts()).thenReturn(TEXT_LIST);
        List<Text> returnedTexts = textController.getTexts();
        verify(textService).allTexts();
        assertEquals(TEXT_LIST,returnedTexts);

    }

    @Test
    void getText() {

        when(textService.getTextById("3")).thenReturn(T3);
        Text retrievedText = textController.getText("3");
        verify(textService).getTextById(eq("3"));
        assertEquals(T3, retrievedText);

    }

    @Test
    void addText() {

        when(textService.addText(T1)).thenReturn(INSERTED_TEXT);

        Text addedText = textService.addText(T1);

        verify(textService).addText(T1);

        assertEquals(INSERTED_TEXT, addedText);

    }
}