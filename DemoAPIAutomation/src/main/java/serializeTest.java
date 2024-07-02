import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {

    public static void main(String[] args) {

        RestAssured.baseURI="https://rahulshettyacademy.com";

        AddPlace ap = new AddPlace();

        ap.setAccuracy(50);
        ap.setAddress("29, side layout, cohen 09");
        ap.setLanguage("English");
        ap.setPhone_number("7824567676");
        ap.setWebsite("http://google.com");
        ap.setName("AshTest");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        ap.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        ap.setLocation(l);
       String res= String.valueOf(given().log().all().queryParam("key","qaclick123")
               .body(ap)
               .when().post("/maps/api/place/add/json").then().assertThat()
                .statusCode(200).extract().response());

       String responseString= res.toString();
       System.out.println(responseString);

    }
}
