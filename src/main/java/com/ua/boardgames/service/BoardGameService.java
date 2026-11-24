package com.ua.boardgames.service;

import com.ua.boardgames.web.dto.BoardGameCardDto;
import com.ua.boardgames.web.dto.BoardGameDetailDto;
import com.ua.boardgames.web.dto.BoardGameRequest;
import java.util.List;

public interface BoardGameService {
    List<BoardGameCardDto> findAll(String category);
    BoardGameDetailDto create(BoardGameRequest request);
    BoardGameDetailDto update(Long id, BoardGameRequest request);
    void delete(Long id);
    BoardGameDetailDto findById(Long id);
}
