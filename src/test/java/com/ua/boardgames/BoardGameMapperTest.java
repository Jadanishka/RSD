package com.ua.boardgames;

import com.ua.boardgames.domain.BoardGame;
import com.ua.boardgames.web.dto.*;
import java.math.BigDecimal;
import com.ua.boardgames.service.mapper.BoardGameMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardGameMapperTest {
    private final BoardGameMapper mapper = new BoardGameMapper();
    private final BoardGame game = new BoardGame(7L, "Chess", "Strategy", 2, 2, 30,
            new BigDecimal("500"), "/images/azul.svg", "Test game");

    @Test
    void toCard() {
        assertEquals(new BoardGameCardDto(7L, "Chess", "Strategy", new BigDecimal("500"),
                "/images/azul.svg"), mapper.toCard(game));
    }

    @Test
    void toDetail() {
        assertEquals(new BoardGameDetailDto(7L, "Chess", "Strategy", 2, 2, 30,
                new BigDecimal("500"), "/images/azul.svg", "Test game"), mapper.toDetail(game));
    }

    @Test
    void toDomain() {
        BoardGameRequest request = new BoardGameRequest("Chess", "Strategy", 2, 2, 30,
                new BigDecimal("500"), "/images/azul.svg", "Test game");
        assertEquals(mapper.toDetail(game), mapper.toDetail(mapper.toDomain(7L, request)));
    }
}
