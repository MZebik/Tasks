package com.crud.tasks.trello.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.scheduler.EmailScheduler;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    TrelloService trelloService;
    @Mock
    TrelloClient trelloClient;
    @Mock
    SimpleEmailService emailService;
    @InjectMocks
    EmailScheduler emailScheduler;
    @Mock
    TaskRepository taskRepository;
    @Autowired
    AdminConfig adminConfig;

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        // When
        List <TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();
        // Then
        assertThat(trelloBoardDtoList).isNotNull();
    }

//    @Test
//    void shouldCreateCard() {
//        // Given
//        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "new test", "test", "test1");
//        Mail mailMock = mock(Mail.class);
//        when(trelloClient.createNewCard(any(TrelloCardDto.class))).thenReturn(new CreatedTrelloCardDto("testId", "test", "http"));
//        doNothing().when(emailService).send(any(Mail.class));
//
//
//        // When
//        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
//        // Then
//        assertEquals("test", createdTrelloCardDto.getName());
//    }

    @Test
    void shouldSendInformationMail() {
        // Given
        when(taskRepository.count()).thenReturn(3l);
        doNothing().when(emailService).send(any(Mail.class));
        // When
        emailScheduler.sendInformationEmail();
        // Then
        verify(emailService).send(any(Mail.class));
    }
}
