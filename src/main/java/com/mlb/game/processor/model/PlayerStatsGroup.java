package com.mlb.game.processor.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStatsGroup {
    private String type;
    private String group;
    private String season;
    private PlayerStats stats;
}