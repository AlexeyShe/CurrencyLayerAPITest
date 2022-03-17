import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SecurityTests {

    private static Response response;

    @Test
    public void basicPlanSecurityTest() {
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT+Consts.ACCESS_KEY+"&source=GBP"+Consts.FORMAT);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(105));
    }

    @Test
    public void nonAuthorizedLiveEndPointTest(){
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(101));
    }

    @Test
    public void nonAuthorizedHistoricalEndPointTest(){
        response = given().get(Consts.BASE_URL + Consts.HISTORICAL_ENDPOINT);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(101));
    }

    @Test
    public void nonAuthorizedConvertEndPointTest(){
        response = given().get(Consts.BASE_URL + Consts.CONVERT_ENDPOINT);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(101));
    }
    @Test
    public void nonAuthorizedTimeframeEndPointTest(){
        response = given().get(Consts.BASE_URL + Consts.TIMEFRAME_ENDPOINT);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(101));
    }
    @Test
    public void nonAuthorizedChangeEndPointTest(){
        response = given().get(Consts.BASE_URL + Consts.CHANGE_ENDPOINT);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(101));
    }
}
