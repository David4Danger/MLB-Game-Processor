package com.mlb.game.processor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore pitcher stats for hitter and vice versa
public class PlayerStats {
    // Populated for both 'hitting' and 'pitching' stat_group
    // Consider stats like hits as hits *for* hitter vs hits *against* pitcher, obp *for* hitter vs obp *against* pitcher
    private Integer gamesPlayed;
    private Integer groundOuts;
    private Integer airOuts;
    private Integer runs;
    private Integer doubles;
    private Integer triples;
    private Integer homeRuns;
    private Integer strikeOuts;
    private Integer baseOnBalls;
    private Integer intentionalWalks;
    private Integer hits;
    private Integer hitByPitch;
    private String avg;
    private Integer atBats;
    private String obp;
    private String slg;
    private String ops;
    private Integer caughtStealing;
    private Integer stolenBases;
    private String stolenBasePercentage;
    private Integer groundIntoDoublePlay;
    private Integer numberOfPitches;
    private Integer sacBunts;
    private Integer sacFlies;
    private String groundOutsToAirouts;

    // Populated for 'hitting' stat_group only
    private Integer plateAppearances;
    private Integer totalBases;
    private Integer rbi;
    private Integer leftOnBase;
    private String babip;
    private String atBatsPerHomeRun;

    // Populated for 'pitching' stat_group only
    private Integer gamesStarted;
    private String era;
    private String inningsPitched;
    private Integer wins;
    private Integer losses;
    private Integer saves;
    private Integer saveOpportunities;
    private Integer holds;
    private Integer earnedRuns;
    private String whip;
    private Integer battersFaced;
    private Integer gamesPitched;
    private Integer completeGames;
    private Integer shutouts;
    private Integer strikes;
    private String strikePercentage;
    private Integer hitBatsmen;
    private Integer balks;
    private Integer wildPitches;
    private Integer pickoffs;
    private String winPercentage;
    private String pitchesPerInning;
    private Integer gamesFinished;
    private String strikeoutWalkRatio;
    private String strikeoutsPer9Inn;
    private String walksPer9Inn;
    private String hitsPer9Inn;
    private String runsScoredPer9;
    private String homeRunsPer9;
    private Integer inheritedRunners;
    private Integer inheritedRunnersScored;
}