package com.mlb.game.processor.service;

import com.mlb.game.processor.model.PlayerStatsResponse;

public interface GameDataService {
    PlayerStatsResponse getPlayerData(String playerName, String year, String statGroup);
}
