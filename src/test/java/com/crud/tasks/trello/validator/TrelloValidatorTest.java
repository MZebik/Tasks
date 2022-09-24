package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ArrayList;

@SpringBootTest
class TrelloValidatorTest {
    @Autowired
    TrelloValidator trelloValidator;

    @Test
    void shouldValidateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "test", "test", "test");
        // When
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    void shouldValidateTrelloBoards() {
        // Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("1", "new board", new ArrayList<>());
        trelloBoardList.add(trelloBoard);
        // When
        trelloValidator.validateTrelloBoards(trelloBoardList);
    }
}