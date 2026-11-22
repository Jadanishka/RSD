package com.ua.boardgames.web;

import com.ua.boardgames.service.BoardGameService;
import com.ua.boardgames.web.dto.BoardGameCardDto;
import com.ua.boardgames.web.dto.BoardGameDetailDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/boardgames")
public class BoardGameController {
    private final BoardGameService service;

    public BoardGameController(BoardGameService service) {
        this.service = service;
    }

    @GetMapping
    public List<BoardGameCardDto> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BoardGameDetailDto getOne(@PathVariable Long id) {
        return service.findById(id);
    }
}
