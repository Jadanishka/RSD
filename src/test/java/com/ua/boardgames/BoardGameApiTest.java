package com.ua.boardgames;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardGameApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllGames() throws Exception {
        mockMvc.perform(get("/api/boardgames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    void getOneGame() throws Exception {
        mockMvc.perform(get("/api/boardgames/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Каркасон"))
                .andExpect(jsonPath("$.minPlayers").value(2));
    }

    @Test
    void getUnknownGame() throws Exception {
        mockMvc.perform(get("/api/boardgames/999"))
                .andExpect(status().isNotFound());
    }
}
