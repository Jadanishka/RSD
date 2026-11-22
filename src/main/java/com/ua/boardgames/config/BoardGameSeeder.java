package com.ua.boardgames.config;

import com.ua.boardgames.domain.BoardGame;
import com.ua.boardgames.repository.BoardGameRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class BoardGameSeeder implements ApplicationRunner {
    private final BoardGameRepository repository;

    public BoardGameSeeder(BoardGameRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {
        repository.save(new BoardGame(null, "Каркасон", "Стратегія", 2, 5, 45,
                new BigDecimal("1250.00"), "/images/carcassonne.svg",
                "Гравці будують міста, дороги та поля з карток місцевості."));
        repository.save(new BoardGame(null, "Діксіт", "Творча", 3, 6, 30,
                new BigDecimal("1390.00"), "/images/dixit.svg",
                "Гравці вигадують асоціації до малюнків і вгадують карти."));
        repository.save(new BoardGame(null, "Кодові імена", "Командна", 2, 8, 20,
                new BigDecimal("890.00"), "/images/codenames.svg",
                "Команди шукають потрібні слова за підказками капітанів."));
        repository.save(new BoardGame(null, "Квиток на потяг", "Сімейна", 2, 5, 60,
                new BigDecimal("1790.00"), "/images/ticket-to-ride.svg",
                "Гравці будують залізничні маршрути між містами."));
        repository.save(new BoardGame(null, "Азул", "Логічна", 2, 4, 35,
                new BigDecimal("1490.00"), "/images/azul.svg",
                "Гравці складають візерунки з кольорових плиток."));
    }
}
