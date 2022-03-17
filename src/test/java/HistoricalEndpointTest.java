import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HistoricalEndpointTest {

    private static Response response;
    private static final String VALID_DATE_1 = "2003-05-07";
    private static final String VALID_DATE_2 = "2017-11-21";


    @AfterEach
    public void setup() {
        System.out.println(response.asString());
    }

    @Test
    public void getHistiricalLiveTest() {
        response = given().get(Consts.BASE_URL + Consts.HISTORICAL_ENDPOINT + Consts.ACCESS_KEY + "&date=" + VALID_DATE_1);
        response.then().statusCode(200);
    }

    @Test
    public void getHistoricalSelectedCurrenciesTest() {
        response = given().get(Consts.BASE_URL + Consts.HISTORICAL_ENDPOINT + Consts.ACCESS_KEY + "&date=" + VALID_DATE_1 + "&currencies=CAD,EUR,RUB,NIO");
        response.then().body("quotes.USDCAD", notNullValue());
        response.then().body("quotes.USDEUR", notNullValue());
        response.then().body("quotes.USDRUB", notNullValue());
        response.then().body("quotes.USDNIO", notNullValue());
        response.then().body("quotes.GBP", equalTo(null));
        response.then().body("quotes.AED", equalTo(null));
    }

    @Test
    public void dateHistoricalTest() {
        response = given().get(Consts.BASE_URL + Consts.HISTORICAL_ENDPOINT + Consts.ACCESS_KEY + "&date=" + VALID_DATE_2 + "&currencies=CAD,EUR,RUB,NIO");
        response.then().body("date", equalTo(VALID_DATE_2));
    }

    @Test
    public void getSpeedHistoricalTest() {
        response = given().get(Consts.BASE_URL + Consts.HISTORICAL_ENDPOINT + Consts.ACCESS_KEY + "&date=" + VALID_DATE_2 + "&currencies=CAD,EUR,RUB,NIO");
        response.then().time(lessThan(1000L));
    }

}
