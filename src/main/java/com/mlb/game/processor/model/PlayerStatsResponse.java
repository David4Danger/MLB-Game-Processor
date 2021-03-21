package com.mlb.game.processor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlayerStatsResponse {
    private Integer id;
    private String first_name;
    private String last_name;
    private Boolean active;
    private String current_team;
    private String position;
    private String nickname;
    private String last_played;
    private String mlb_debut;
    private String bat_side;
    private String pitch_hand; // doubles as throw hand for non-pitcher
    private List<PlayerStatsGroup> stats;
}