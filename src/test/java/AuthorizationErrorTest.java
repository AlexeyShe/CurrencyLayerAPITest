import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthorizationErrorTest {

//    @Test
//    public void invalidTokenTest(){
//        Response response = given().auth().oauth2("blabla").contentType("application/json").get(Consts.URL + Consts.MOVIES_ENDPOINT);
//        System.out.println(response.asString());
//        response.then().statusCode(401);
//        response.then().body("message", equalTo("Unauthorized."));
//        response.then().body("message", containsString("Unautho"));
//
//    }

}
