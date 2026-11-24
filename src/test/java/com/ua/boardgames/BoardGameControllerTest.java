package com.ua.boardgames;

import com.ua.boardgames.exception.BoardGameNotFoundException;
import com.ua.boardgames.service.BoardGameService;
import com.ua.boardgames.web.BoardGameController;
import com.ua.boardgames.web.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BoardGameController.class)
class BoardGameControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BoardGameService service;
    private final BoardGameDetailDto detail = new BoardGameDetailDto(7L, "Chess", "Strategy",
            2, 2, 30, new BigDecimal("500"), "/images/azul.svg", "Test game");
    private final String body = """
            {"title":"Chess","category":"Strategy","minPlayers":2,"maxPlayers":2,
            "durationMinutes":30,"price":500,"coverUrl":"/images/azul.svg","description":"Test game"}
            """;

    @Test
    void list() throws Exception {
        when(service.findAll(null)).thenReturn(List.of(new BoardGameCardDto(7L, "Chess", "Strategy",
                new BigDecimal("500"), "/images/azul.svg")));
        mockMvc.perform(get("/api/boardgames")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getOne() throws Exception {
        when(service.findById(7L)).thenReturn(detail);
        mockMvc.perform(get("/api/boardgames/7")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7));
    }

    @Test
    void filter() throws Exception {
        when(service.findAll("Strategy")).thenReturn(List.of());
        mockMvc.perform(get("/api/boardgames").param("category", "Strategy"))
                .andExpect(status().isOk());
        verify(service).findAll("Strategy");
    }

    @Test
    void create() throws Exception {
        when(service.create(any(BoardGameRequest.class))).thenReturn(detail);
        mockMvc.perform(post("/api/boardgames").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(7));
    }

    @Test
    void update() throws Exception {
        when(service.update(eq(7L), any(BoardGameRequest.class))).thenReturn(detail);
        mockMvc.perform(put("/api/boardgames/7").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Chess"));
    }

    @Test
    void delete_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/boardgames/7")).andExpect(status().isNoContent())
                .andExpect(content().string(""));
        verify(service).delete(7L);
    }

    @Test
    void notFound() throws Exception {
        when(service.findById(99L)).thenThrow(new BoardGameNotFoundException(99L));
        mockMvc.perform(get("/api/boardgames/99")).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404)).andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").isNotEmpty()).andExpect(jsonPath("$.details").isMap());
    }

    @Test
    void validation() throws Exception {
        mockMvc.perform(post("/api/boardgames").contentType(MediaType.APPLICATION_JSON)
                .content(body.replace("Chess", "").replace("500", "-1")))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.details.title").exists()).andExpect(jsonPath("$.details.price").exists());
        verifyNoInteractions(service);
    }
}
