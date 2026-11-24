package com.ua.boardgames.web;

import com.ua.boardgames.service.BoardGameService;
import com.ua.boardgames.web.dto.BoardGameCardDto;
import com.ua.boardgames.web.dto.BoardGameDetailDto;
import com.ua.boardgames.web.dto.BoardGameRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameController {
    private final BoardGameService service;

    public BoardGameController(BoardGameService service) {
        this.service = service;
    }

    @GetMapping
    public List<BoardGameCardDto> list(@RequestParam(required = false) String category) {
        return service.findAll(category);
    }

    @GetMapping("/{id}")
    public BoardGameDetailDto getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardGameDetailDto create(@Valid @RequestBody BoardGameRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public BoardGameDetailDto update(@PathVariable Long id, @Valid @RequestBody BoardGameRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
