package com.mlb.game.processor.constants;

public class ProcessorConstants {
    public ProcessorConstants() {
        throw new RuntimeException("Constants class should not be instantiated!");
    }

    // VALIDATION
    public static final String PITCHING_STAT_GROUP = "pitching";
    public static final String HITTING_STAT_GROUP = "hitting";
    public static final String YEAR_CAREER = "career";
    public static final int EARLIEST_VALID_YEAR = 1920;// Honestly I have no idea what the earliest year for the API is so I'll go with the end of the dead ball ERA

    // API CALL
    public static final String PLAYER_NAME = "player_name";
    public static final String YEAR = "year";
    public static final String STAT_GROUP = "stat_group";
}
