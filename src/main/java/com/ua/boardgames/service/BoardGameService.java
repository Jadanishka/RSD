package com.ua.boardgames.service;

import com.ua.boardgames.web.dto.BoardGameCardDto;
import com.ua.boardgames.web.dto.BoardGameDetailDto;
import java.util.List;

public interface BoardGameService {
    List<BoardGameCardDto> findAll();
    BoardGameDetailDto findById(Long id);
}
