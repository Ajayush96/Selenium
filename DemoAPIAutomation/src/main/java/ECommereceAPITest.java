import POJO.LoginRequest;
import POJO.LoginResponse;
import POJO.OrderDetails;
import POJO.Orders;
import files.ReUsableMethods;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommereceAPITest {
    public static void main(String[] args) {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("ajayush96@gmail.com");
        loginRequest.setUserPassword("Test@123");

        RequestSpecification reqLogin = given().spec(req).body(loginRequest);
        LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);

        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getUserId());


        //Add Product
        RequestSpecification addProductBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                addHeader("authorization",loginResponse.getToken()).build();
        RequestSpecification reqAddProduct=given().log().all().spec(addProductBasereq).param("productName", "qwerty_1").param("productAddedBy", loginResponse.getUserId())
                .param("productCategory", "fashion").param("productSubCategory", "shirts").param("productPrice","1500").param("productDescription","Adidas").param("productFor","women")
                .multiPart("productImage",new File("//home//ashish//Downloads//download (1).png"));

        String addProductResponse= reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
        JsonPath js= ReUsableMethods.rawToJson(addProductResponse);
        String productId=js.get("productId");

        System.out.println(productId);

        //Create Product
        RequestSpecification createOrderBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                addHeader("authorization",loginResponse.getToken()).setContentType(ContentType.JSON).build();


        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(productId);

        List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
        orderDetailList.add(orderDetails);
        Orders order = new Orders();
        order.setOrders(orderDetailList);
       RequestSpecification createOrderReq= given().log().all().spec(createOrderBasereq).body(order);

      String responseAddOrder= createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
      System.out.println(responseAddOrder);

      JsonPath js2 = ReUsableMethods.rawToJson(responseAddOrder);
      List<String> orders=js2.getList("orders");

      //Get View Details
        RequestSpecification viewOrderBaseDetails = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                addHeader("authorization",loginResponse.getToken()).build();

        RequestSpecification viewOrderDetails=given().log().all().spec(viewOrderBaseDetails).param("id",orders);

       String  responseViewOrder= viewOrderDetails.when().get("/api/ecom/order/get-orders-details").then().log().all().extract().response().asString();
       System.out.println(responseViewOrder);

       //Delete the product
        RequestSpecification deleteOrderBaseDetails = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                addHeader("authorization",loginResponse.getToken()).setContentType(ContentType.JSON).build();

        RequestSpecification delReq=given().log().all().spec(deleteOrderBaseDetails).pathParam("productId",productId);

       String delResponse= delReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
       System.out.println(delResponse);




    }
}
