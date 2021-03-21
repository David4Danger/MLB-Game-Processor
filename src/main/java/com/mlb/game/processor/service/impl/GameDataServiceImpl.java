package com.mlb.game.processor.service.impl;

import com.amazonaws.http.HttpMethodName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlb.game.processor.model.PlayerStatsResponse;
import com.mlb.game.processor.service.GameDataService;
import com.mlb.game.processor.util.ApiGatewayResponse;
import com.mlb.game.processor.util.JsonApiGatewayCaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static com.mlb.game.processor.constants.ProcessorConstants.*;

@Service
public class GameDataServiceImpl implements GameDataService {
    @Value("${aws.mlb-game-lambda.player-data-resource}")
    private String awsMlbGameLambdaPlayerDataResource;

    JsonApiGatewayCaller jsonApiGatewayCaller;

    public GameDataServiceImpl(@Value("${aws.region}") String awsRegion,
                               @Value("${aws.mlb-game-lambda.endpoint}") String awsMlbGameLambdaEndpoint,
                               @Value("${aws.accessKey}") String awsAccessKey,
                               @Value("${aws.secretKey}") String awsSecretKey) {
        try {
            jsonApiGatewayCaller = new JsonApiGatewayCaller(awsAccessKey, awsSecretKey, awsRegion, new URI(awsMlbGameLambdaEndpoint));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("Couldn't create jsonApiGatewayCaller, reason is " + e.getReason());
        }
    }

    public PlayerStatsResponse getPlayerData(String playerName, String year, String statGroup) {
        ObjectMapper objectMapper = new ObjectMapper();
        PlayerStatsResponse playerStatsResponse = null;

        Map<String, List<String>> queryStringMap = buildGetPlayerDataQueryStringMap(playerName, year, statGroup);
        ApiGatewayResponse response = jsonApiGatewayCaller.execute(HttpMethodName.GET,
                awsMlbGameLambdaPlayerDataResource, queryStringMap);
        try {
            playerStatsResponse = objectMapper.readValue(response.getBody(), PlayerStatsResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Couldn't create map ApiGatewayResponse to PlayerStatsResponse, reason is " + e.getMessage());
        }
        return playerStatsResponse;
    }

    private Map<String, List<String>> buildGetPlayerDataQueryStringMap(String playerName, String year, String statGroup) {
        Map<String, List<String>> queryMap = new HashMap<>();
        final String[] splitPlayerName = playerName.split("[_ ]");
        final String formattedPlayerName = splitPlayerName[1] + ", " + splitPlayerName[0];
        final String formattedYear = year.equalsIgnoreCase("career") ? "~" : year;
        queryMap.put(PLAYER_NAME, Collections.singletonList(formattedPlayerName));
        queryMap.put(YEAR, Collections.singletonList(formattedYear));
        queryMap.put(STAT_GROUP, Collections.singletonList(statGroup));
        return queryMap;
    }
}
