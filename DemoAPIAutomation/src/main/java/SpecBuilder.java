import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class SpecBuilder {

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

        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
                        .setContentType(ContentType.JSON).build();

        ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        RequestSpecification res = given().spec(req).body(ap);

               Response response=res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();

       String responseString= response.asString();
       System.out.println(responseString);

    }
}
