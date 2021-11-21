package api;

import enity.PlayMode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiEndpoints {

//    private static final String BASE_URL_LOCALHOST = "http://localhost:8080/casino/";
    private static final String BASE_URL = "http://95.217.177.249/casino/";
    private static final String CREATE_USER = "createacc?id={playerId}";
    private static final String PLAY = "play{playMode}?id={playerId}&bet={amountOfMoney}&number={randomNumber}";


    public Response createPlayer(long playerId){
        return given().pathParam("playerId", playerId)
                .get(CREATE_USER).then().extract().response();
    }

    public Response play(PlayMode mode, long playerId, int bet, long randomNumber){
        return given()
                .pathParam("playMode", mode)
                .pathParam("playerId", playerId)
                .pathParam("amountOfMoney", bet)
                .pathParam("randomNumber", randomNumber)
                .get(PLAY).then().extract().response();
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }
}
