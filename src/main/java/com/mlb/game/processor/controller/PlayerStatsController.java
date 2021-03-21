package com.mlb.game.processor.controller;

import com.mlb.game.processor.model.PlayerStatsResponse;
import com.mlb.game.processor.service.GameDataService;
import com.mlb.game.processor.validation.PlayerStatsRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlayerStatsController {
    @Autowired
    GameDataService gameDataService;

    @Autowired
    PlayerStatsRequestValidator validator;

    @ResponseBody
    @GetMapping("/get-player-stats")
    public ResponseEntity getPlayerStats(@RequestParam(value="playerName") String playerName,
                                         @RequestParam(value="year") String year,
                                         @RequestParam(value="statGroup") String statGroup) {
        String inputError = validator.invalidInputError(playerName, year, statGroup);
        if (null != inputError) {
            return ResponseEntity.badRequest().body(inputError);
        }

        PlayerStatsResponse playerStatsResponse = gameDataService.getPlayerData(playerName, year, statGroup);
        return ResponseEntity.ok(playerStatsResponse);
    }

}
