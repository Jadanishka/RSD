package com.ua.boardgames;

import com.ua.boardgames.domain.BoardGame;
import com.ua.boardgames.web.dto.*;
import java.math.BigDecimal;
import com.ua.boardgames.repository.BoardGameRepository;
import com.ua.boardgames.service.BoardGameService;
import com.ua.boardgames.service.BoardGameServiceImpl;
import com.ua.boardgames.service.mapper.BoardGameMapper;
import com.ua.boardgames.exception.BoardGameNotFoundException;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardGameServiceImplTest {
    private final BoardGameRepository repository = mock(BoardGameRepository.class);
    private final BoardGameService service = new BoardGameServiceImpl(repository, new BoardGameMapper());
    private final BoardGameRequest request = new BoardGameRequest("Chess", "Strategy", 2, 2, 30,
            new BigDecimal("500"), "/images/azul.svg", "Test game");
    private final BoardGame game = new BoardGameMapper().toDomain(12L, request);

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(game));
        assertEquals(1, service.findAll(null).size());
        assertEquals("Chess", service.findAll(null).get(0).title());
    }

    @Test
    void filter() {
        when(repository.findByCategory("Strategy")).thenReturn(List.of(game));
        assertEquals("Strategy", service.findAll("Strategy").get(0).category());
        verify(repository).findByCategory("Strategy");
    }

    @Test
    void findOne() {
        when(repository.findById(12L)).thenReturn(Optional.of(game));
        assertEquals(12L, service.findById(12L).id());
    }

    @Test
    void missingGame() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(BoardGameNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void create() {
        when(repository.save(any(BoardGame.class))).thenAnswer(call -> {
            BoardGame saved = call.getArgument(0);
            assertNull(saved.getId());
            saved.setId(12L);
            return saved;
        });
        assertEquals(12L, service.create(request).id());
        verify(repository).save(any(BoardGame.class));
    }

    @Test
    void update() {
        when(repository.findById(12L)).thenReturn(Optional.of(game));
        when(repository.save(any(BoardGame.class))).thenAnswer(call -> call.getArgument(0));
        BoardGameDetailDto result = service.update(12L, request);
        assertEquals(12L, result.id());
        assertEquals("Chess", result.title());
    }

    @Test
    void updateMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(BoardGameNotFoundException.class, () -> service.update(99L, request));
        verify(repository, never()).save(any());
    }

    @Test
    void delete() {
        when(repository.findById(12L)).thenReturn(Optional.of(game));
        service.delete(12L);
        verify(repository).deleteById(12L);
    }

    @Test
    void deleteMissing() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(BoardGameNotFoundException.class, () -> service.delete(99L));
        verify(repository, never()).deleteById(any());
    }
}
