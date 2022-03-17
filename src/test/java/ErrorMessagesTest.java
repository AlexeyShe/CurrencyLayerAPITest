import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ErrorMessagesTest {

    private static Response response;


    @Test
    public void error101Test(){
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(101));
    }

    @Test
    public void basicPlanSecurityTest() {
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT+Consts.ACCESS_KEY+"&source=GBP"+Consts.FORMAT);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(105));
    }

    @Test
    public void error103Test () {
        response = given().get(Consts.BASE_URL + "liver"+Consts.ACCESS_KEY);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(103));
    }


    @Test
    public void error106Test () {
        response = given().get(Consts.BASE_URL + Consts.HISTORICAL_ENDPOINT+Consts.ACCESS_KEY+"&date=1998-01-01&currencies=aud");
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(106));
    }

    @Test
    public void error202Test () {
        response = given().get(Consts.BASE_URL + Consts.HISTORICAL_ENDPOINT+Consts.ACCESS_KEY+"&currencies=NIS");
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(202));
    }

    @Test
    public void error301Test () {
        response = given().get(Consts.BASE_URL+Consts.HISTORICAL_ENDPOINT +Consts.ACCESS_KEY+"&date=");
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(301));
    }

    @Test
    public void error302FutureDateTest () {
        response = given().get(Consts.BASE_URL+Consts.HISTORICAL_ENDPOINT +Consts.ACCESS_KEY+"&date=2023-01-02");
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(302));
    }

    @Test
    public void error302DateBefore1999HistoricalTest () {
        response = given().get(Consts.BASE_URL+Consts.HISTORICAL_ENDPOINT +Consts.ACCESS_KEY+"&date=1998-12-31");
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(302));
    }

    @Test
    public void error302InvalidDate () {
        response = given().get(Consts.BASE_URL+Consts.HISTORICAL_ENDPOINT +Consts.ACCESS_KEY+"&date=1998-45-31");
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(302));
    }

}


