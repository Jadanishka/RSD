package com.ua.boardgames;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.boardgames.repository.BoardGameRepository;
import com.ua.boardgames.web.dto.BoardGameRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BoardGameApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardGameRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void clearState() {
        repository.deleteAll();
    }

    private String body(String title, String category, String price) throws Exception {
        return objectMapper.writeValueAsString(new BoardGameRequest(title, category, 2, 4, 30,
                new BigDecimal(price), "/images/azul.svg", "Test game"));
    }

    private long givenGame(String title, String category) throws Exception {
        String response = mockMvc.perform(post("/api/boardgames").contentType(MediaType.APPLICATION_JSON)
                .content(body(title, category, "500"))).andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void listAndFilter() throws Exception {
        long id = givenGame("Chess", "Strategy");
        givenGame("Dixit", "Creative");
        mockMvc.perform(get("/api/boardgames")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
        mockMvc.perform(get("/api/boardgames").param("category", "Strategy"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(id));
        mockMvc.perform(get("/api/boardgames/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Chess"));
    }

    @Test
    void update() throws Exception {
        long id = givenGame("Chess", "Strategy");
        mockMvc.perform(put("/api/boardgames/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(body("New Chess", "Strategy", "750"))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
        mockMvc.perform(get("/api/boardgames/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Chess")).andExpect(jsonPath("$.price").value(750));
    }

    @Test
    void deleteAndNotFound() throws Exception {
        long id = givenGame("Chess", "Strategy");
        mockMvc.perform(delete("/api/boardgames/{id}", id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/boardgames/{id}", id)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404)).andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").isNotEmpty()).andExpect(jsonPath("$.details").isMap());
        mockMvc.perform(put("/api/boardgames/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(body("Chess", "Strategy", "500"))).andExpect(status().isNotFound());
        mockMvc.perform(delete("/api/boardgames/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    void invalidData() throws Exception {
        mockMvc.perform(post("/api/boardgames").contentType(MediaType.APPLICATION_JSON)
                .content(body("", "", "-1"))).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400)).andExpect(jsonPath("$.details.title").exists())
                .andExpect(jsonPath("$.details.category").exists()).andExpect(jsonPath("$.details.price").exists());
        mockMvc.perform(get("/api/boardgames")).andExpect(jsonPath("$.length()").value(0));
    }
}
