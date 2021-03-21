package com.mlb.game.processor.service.impl;

import com.amazonaws.http.HttpMethodName;
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

    public void getPlayerData(String playerName, String year, String statGroup) {
        Map<String, List<String>> queryStringMap = buildGetPlayerDataQueryStringMap(playerName, year, statGroup);
        ApiGatewayResponse response = jsonApiGatewayCaller.execute(HttpMethodName.GET,
                awsMlbGameLambdaPlayerDataResource, queryStringMap);
        System.out.println(response.getBody());
    }

    private Map<String, List<String>> buildGetPlayerDataQueryStringMap(String playerName, String year, String statGroup) {
        Map<String, List<String>> queryMap = new HashMap<>();
        queryMap.put(PLAYER_NAME, Collections.singletonList(playerName));
        queryMap.put(YEAR, Collections.singletonList(year));
        queryMap.put(STAT_GROUP, Collections.singletonList(statGroup));
        return queryMap;
    }
}
