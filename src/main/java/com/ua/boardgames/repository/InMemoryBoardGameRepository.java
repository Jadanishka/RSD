package com.ua.boardgames.repository;

import com.ua.boardgames.domain.BoardGame;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryBoardGameRepository implements BoardGameRepository {
    private final Map<Long, BoardGame> games = new LinkedHashMap<>();
    private long nextId = 1;

    @Override
    public List<BoardGame> findAll() {
        return new ArrayList<>(games.values());
    }

    @Override
    public Optional<BoardGame> findById(Long id) {
        return Optional.ofNullable(games.get(id));
    }

    @Override
    public BoardGame save(BoardGame game) {
        if (game.getId() == null) {
            game.setId(nextId);
            nextId++;
        }
        games.put(game.getId(), game);
        return game;
    }
}
