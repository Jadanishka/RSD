package com.ua.boardgames.domain;

import java.math.BigDecimal;

public class BoardGame {
    private Long id;
    private String title;
    private String category;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Integer durationMinutes;
    private BigDecimal price;
    private String coverUrl;
    private String description;

    public BoardGame(Long id, String title, String category, Integer minPlayers,
                     Integer maxPlayers, Integer durationMinutes, BigDecimal price,
                     String coverUrl, String description) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.durationMinutes = durationMinutes;
        this.price = price;
        this.coverUrl = coverUrl;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getDescription() {
        return description;
    }
}
