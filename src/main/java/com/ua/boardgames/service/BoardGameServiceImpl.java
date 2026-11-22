package com.ua.boardgames.service;

import com.ua.boardgames.domain.BoardGame;
import com.ua.boardgames.exception.BoardGameNotFoundException;
import com.ua.boardgames.repository.BoardGameRepository;
import com.ua.boardgames.service.mapper.BoardGameMapper;
import com.ua.boardgames.web.dto.BoardGameCardDto;
import com.ua.boardgames.web.dto.BoardGameDetailDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardGameServiceImpl implements BoardGameService {
    private final BoardGameRepository repository;
    private final BoardGameMapper mapper;

    public BoardGameServiceImpl(BoardGameRepository repository, BoardGameMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<BoardGameCardDto> findAll() {
        List<BoardGameCardDto> result = new ArrayList<>();
        for (BoardGame game : repository.findAll()) {
            result.add(mapper.toCard(game));
        }
        return result;
    }

    @Override
    public BoardGameDetailDto findById(Long id) {
        Optional<BoardGame> game = repository.findById(id);
        if (game.isEmpty()) {
            throw new BoardGameNotFoundException(id);
        }
        return mapper.toDetail(game.get());
    }
}
