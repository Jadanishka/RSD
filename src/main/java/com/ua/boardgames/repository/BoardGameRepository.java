package com.ua.boardgames.repository;

import com.ua.boardgames.domain.BoardGame;
import java.util.List;
import java.util.Optional;

public interface BoardGameRepository {
    List<BoardGame> findAll();
    Optional<BoardGame> findById(Long id);
    BoardGame save(BoardGame game);
    List<BoardGame> findByCategory(String category);
    void deleteById(Long id);
    void deleteAll();
}
