package stepdef;



import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static org.junit.Assert.*;


public class Products
{
    public int Statuscode;
    public RequestSpecification httpRequest;
    public Response response;
    public int Responsecode;
    public ResponseBody body;
    public JSONObject requestParams;

    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint()
    {
        RestAssured.baseURI= "https://fakestoreapi.com/";

    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request()
    {
        httpRequest = RestAssured.given();
        response= httpRequest.get("Products");
    }
    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1)
    {
        Responsecode=  response.getStatusCode();
        assertEquals(Responsecode, 200);
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String rate)
    {
        body=response.getBody();

        // convert response body to String
        String responsebody= body.asString();

        // JSON Representation from Response Body
        JsonPath jsnpath=response.jsonPath();

        String s= jsnpath.getJsonObject("rating[0].rate").toString();

        assertEquals(rate, s);

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }

    @Given("I hit the url of post product api endpoint")
    public void iHitTheUrlOfPostProductApiEndpoint()
    {
        RestAssured.baseURI= "https://fakestoreapi.com/";
        httpRequest=RestAssured.given();
        requestParams=new JSONObject();

    }

    @And("I pass the request body of product title {}")
    public void iPassTheRequestBodyOfProductTitleProductTitle(String title)
    {
        requestParams.put("title", title);
        requestParams.put("price", 13.5);
        requestParams.put("description", "lorem ipsum set");
        requestParams.put("image", "https://i.pravatar.cc");
        requestParams.put("caregory", "electronic");
        httpRequest.body(requestParams.toJSONString());
        Response response=httpRequest.post("products");
        ResponseBody body=response.getBody();

        JsonPath jsnpath=response.jsonPath();
        String s=jsnpath.getJsonObject("id").toString();
        assertEquals("21", s);

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAsId(String id)
    {
    }

    @Given("I hit the url of put product api endpoint")
    public void iHitTheUrlOfPutProductApiEndpoint()
    {
        RestAssured.baseURI= "https://fakestoreapi.com/";
        requestParams=new JSONObject();

    }

    @When("I pass the url of products in the request with {}")
    public void iPassTheUrlOfProductsInTheRequestWithProductnumber(String productnumber)
    {
        httpRequest = RestAssured.given();

        requestParams.put("title", "test product");
        requestParams.put("price", 13.5);
        requestParams.put("description", "lorem ipsum set");
        requestParams.put("image", "https://i.pravatar.cc");
        requestParams.put("caregory", "electronic");

        httpRequest.body(requestParams.toJSONString());
        response= httpRequest.put("products/"+ productnumber);
        ResponseBody body=response.getBody();

        JsonPath jsnpath=response.jsonPath();
        String s=jsnpath.getJsonObject("id").toString();
        assertEquals("5", s);

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }

}
