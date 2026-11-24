package com.ua.boardgames.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record BoardGameRequest(
        @NotBlank String title,
        @NotBlank String category,
        @NotNull @Positive Integer minPlayers,
        @NotNull @Positive Integer maxPlayers,
        @NotNull @Positive Integer durationMinutes,
        @NotNull @Positive BigDecimal price,
        String coverUrl,
        String description) {
}
