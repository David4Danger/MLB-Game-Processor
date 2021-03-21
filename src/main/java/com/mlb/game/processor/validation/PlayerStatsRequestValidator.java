package com.mlb.game.processor.validation;

import org.springframework.stereotype.Component;

import java.util.Calendar;

import static com.mlb.game.processor.constants.ProcessorConstants.*;

@Component
public class PlayerStatsRequestValidator {
    private static final String invalidStatGroup = "statGroup parameter was invalid value: %s, must be 'pitching' or 'hitting'!";
    private static final String invalidYear = "year parameter was invalid value: %s, must be 'career' or 'YYYY' between 1920 and current year, inclusive.";
    private static final String invalidPlayerName = "playerName parameter was invalid value: %s, must be format 'First Last' or 'First_Last', case insensitive.";

    public String invalidInputError(String playerName, String year, String statGroup) {
        if (!PITCHING_STAT_GROUP.equals(statGroup) && !HITTING_STAT_GROUP.equals(statGroup)) {
            return String.format(invalidStatGroup, statGroup);
        } else if (!YEAR_CAREER.equals(year) && !isValidYear(year)) {
            return String.format(invalidYear, year);
        } else if (!isValidName(playerName)) {
            return String.format(invalidPlayerName, playerName);
        }
        return null;
    }

    private Boolean isValidYear(String year) {
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int numericInputYear;
        try {
            numericInputYear = Integer.parseInt(year);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return EARLIEST_VALID_YEAR <= numericInputYear && numericInputYear <= currentYear;
    }

    private Boolean isValidName(String playerName) {
        String[] splitName = playerName.split("[_ ]");//match "_" or " "
        return splitName.length == 2;
    }
}
