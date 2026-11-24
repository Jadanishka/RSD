package com.ua.boardgames.service.mapper;

import com.ua.boardgames.domain.BoardGame;
import com.ua.boardgames.web.dto.BoardGameCardDto;
import com.ua.boardgames.web.dto.BoardGameDetailDto;
import com.ua.boardgames.web.dto.BoardGameRequest;
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
    public BoardGame toDomain(Long id, BoardGameRequest request) {
        return new BoardGame(id, request.title(), request.category(),
                request.minPlayers(), request.maxPlayers(), request.durationMinutes(),
                request.price(), request.coverUrl(), request.description());
    }
}
