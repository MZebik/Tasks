package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void shouldReturnTrelloBoardDToList() {
        // Given
        List<TrelloList> trelloList = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("board1", "New test board", trelloList);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        // When
        TrelloBoardDto trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList).get(0);
        // Then
        assertEquals("board1", trelloBoardDto.getId());
        assertEquals(1, trelloBoardList.size());
    }

    @Test
    void shouldReturnTrelloBoard() {
        // Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("board1", "New test board", trelloList);
        List<TrelloBoardDto> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoardDto);
        // When
        TrelloBoard trelloBoard = trelloMapper.mapToBoards(trelloBoardList).get(0);
        // Then
        assertEquals("board1", trelloBoard.getId());
    }

    @Test
    void shouldReturnList() {
        // Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("list1", "test list", true);
        trelloList.add(trelloListDto);
        // When
        TrelloList trelloList1 = trelloMapper.mapToList(trelloList).get(0);
        // Then
        assertEquals("test list", trelloList1.getName());
    }

    @Test
    void shouldReturnListDto() {
        // Given
        List<TrelloList> trelloList = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("list1", "test list", true);
        trelloList.add(trelloList1);
        // When
        TrelloListDto trelloListDto = trelloMapper.mapToListDto(trelloList).get(0);
        // Then
        assertEquals("test list", trelloListDto.getName());
        assertEquals(true, trelloListDto.isClosed());
    }

    @Test
    void shouldReturnCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("Test Card", "New test card", "test", "list1");
        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        // Then
        assertEquals("Test Card", trelloCardDto.getName());
        assertEquals("test", trelloCardDto.getPos());
    }

    @Test
    void shouldReturnCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test Card", "New test card", "test", "list1");
        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        // Then
        assertEquals("New test card", trelloCard.getDescription());
        assertEquals("list1", trelloCardDto.getListId());
    }
}
