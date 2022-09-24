package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloConfigTest {
    @Autowired
    TrelloConfig trelloConfig;

    @Test
    void shouldReturnValues() {
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        String trelloToken = trelloConfig.getTrelloToken();
        String trelloUser = trelloConfig.getTrelloUser();

        assertEquals("https://api.trello.com/1", endpoint);
        assertEquals("00b62d2d204764e6eadd16b5d2ed8c5f", trelloAppKey);
        assertEquals("efc22b013e1d7ab4282f37cd454c8c55e37b78a4605c2b19a12f575537f4b3d9", trelloToken);
        assertEquals("mateuszzebik", trelloUser);
    }
}