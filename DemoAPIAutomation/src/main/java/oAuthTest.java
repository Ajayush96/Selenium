import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
import files.ReUsableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
public class oAuthTest {
    public static void main(String[] args) {

        String [] CourseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};

    String response=given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
            formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type","client_credentials")
            .formParams("scope","trust").when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

    System.out.println(response);

        JsonPath js=ReUsableMethods.rawToJson(response);
        String Access_Token=js.getString("access_token");

        System.out.println(Access_Token);

        //String response2=given().queryParam("access_token",Access_Token).when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
        GetCourse gc=given().queryParam("access_token",Access_Token).when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

        //System.out.println(response2);
        System.out.println("This is LinkedIN: "+gc.getLinkedIn());
        System.out.println("This is Instructor: "+gc.getInstructor());

        System.out.println("Capturing the API course title: "+  gc.getCourses().getApi().get(1).getCourseTitle());

        List<Api> apiCourses=gc.getCourses().getApi();

        for(int i=0;i<apiCourses.size();i++)
        {
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println("Print the price for 2nd index of API: "+apiCourses.get(i).getPrice());
            }
        }
        ArrayList<String> a=new ArrayList<String>();
        List<WebAutomation> webCourses=gc.getCourses().getWebAutomation();
        for(int i=0;i<webCourses.size();i++)
        {
            System.out.println("Here are the courses: " + webCourses.get(i).getCourseTitle());
            a.add(webCourses.get(i).getCourseTitle());
        }
        List<String> expectedList=Arrays.asList(CourseTitles);

        Assert.assertTrue(a.equals(expectedList));
    }
}
