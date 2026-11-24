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
    public synchronized List<BoardGame> findAll() {
        return new ArrayList<>(games.values());
    }

    @Override
    public synchronized Optional<BoardGame> findById(Long id) {
        return Optional.ofNullable(games.get(id));
    }

    @Override
    public synchronized BoardGame save(BoardGame game) {
        if (game.getId() == null) {
            game.setId(nextId);
            nextId++;
        }
        games.put(game.getId(), game);
        return game;
    }
    @Override
    public synchronized List<BoardGame> findByCategory(String category) {
        List<BoardGame> result = new ArrayList<>();
        for (BoardGame game : games.values()) {
            if (game.getCategory().equalsIgnoreCase(category)) {
                result.add(game);
            }
        }
        return result;
    }

    @Override
    public synchronized void deleteById(Long id) {
        games.remove(id);
    }

    @Override
    public synchronized void deleteAll() {
        games.clear();
    }
}
