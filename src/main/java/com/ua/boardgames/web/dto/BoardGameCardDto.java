package com.ua.boardgames.web.dto;

import java.math.BigDecimal;

public record BoardGameCardDto(Long id, String title, String category,
                               BigDecimal price, String coverUrl) {
}
