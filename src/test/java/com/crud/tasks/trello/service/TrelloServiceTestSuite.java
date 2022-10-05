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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

    @MockBean
    TrelloClient trelloClient;
    @MockBean
    SimpleEmailService emailService;
    @InjectMocks
    EmailScheduler emailScheduler;
    @MockBean
    TaskRepository taskRepository;
    @MockBean
    AdminConfig adminConfig;
    @InjectMocks
    TrelloService trelloService;

    @BeforeEach
    public void prepare() {
        trelloService=null;
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        // When
        List <TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();
        // Then
        assertThat(trelloBoardDtoList).isNotNull();
    }

    @Test
    void shouldCreateCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "new test", "test", "test1");
        when(trelloClient.createNewCard(any())).thenReturn(new CreatedTrelloCardDto("testId", "test", "http"));
        doNothing().when(emailService).send(any(Mail.class));


        // When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
        // Then
        assertEquals("test", createdTrelloCardDto.getName());
    }

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
