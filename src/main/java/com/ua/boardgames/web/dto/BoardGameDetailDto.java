package com.ua.boardgames.web.dto;

import java.math.BigDecimal;

public record BoardGameDetailDto(Long id, String title, String category,
                                 Integer minPlayers, Integer maxPlayers,
                                 Integer durationMinutes, BigDecimal price,
                                 String coverUrl, String description) {
}
