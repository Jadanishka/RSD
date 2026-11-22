package com.ua.boardgames.service.mapper;

import com.ua.boardgames.domain.BoardGame;
import com.ua.boardgames.web.dto.BoardGameCardDto;
import com.ua.boardgames.web.dto.BoardGameDetailDto;
import org.springframework.stereotype.Component;

@Component
public class BoardGameMapper {
    public BoardGameCardDto toCard(BoardGame game) {
        return new BoardGameCardDto(game.getId(), game.getTitle(),
                game.getCategory(), game.getPrice(), game.getCoverUrl());
    }

    public BoardGameDetailDto toDetail(BoardGame game) {
        return new BoardGameDetailDto(game.getId(), game.getTitle(),
                game.getCategory(), game.getMinPlayers(), game.getMaxPlayers(),
                game.getDurationMinutes(), game.getPrice(),
                game.getCoverUrl(), game.getDescription());
    }
}
