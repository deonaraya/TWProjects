package com.deo.tw.api;


import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class firstTest {

    ArrayList arr = new ArrayList() ;


    public String createSsoToken() {

        // get("https://t2-qa.xkit.co/knowledgecenter/index.html").then().body(hasItem(" Cisco Collaborative Knowledge"));
        //  given().auth().basic("admin", "Cisco@!admin").when().get("https://t2-qa.xkit.co/knowledgecenter/tenants").then().body();


        Response res = given().
               // config(RestAssuredConfig.config().config().encoderConfig(EncoderConfig.encoderConfig().encoderConfig().encodeContentTypeAs("no-cache", ContentType.TEXT))).
                header("Content-Type", "application/x-www-form-urlencoded").
                body("username=cisco-knowledge.svcAccount&password=56juWFWWIgnSf..Ggkjeiu8.2lo0976uDFF44Ma452016&uri=realm%3D%2Fcommon_identity_operations%26module%3DserviceAccounts").
                when().
                post("https://idbrokerbts.webex.com/idb/identity/authenticate").then().
                extract().response();

        String tokenId = res.body().asString() ;
        String token = tokenId.substring(9,tokenId.length()-1);
        System.out.println(token);

        String tk = "ssoToken " + token ;
        System.out.println(tk);

       return tk ;
    }


    @Test
    public void newTest1(){

        Response response = given().
                //config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("application/json", ContentType.TEXT))).
                header("Content-Type","application/json").
                body("{\"email\":\"rsangara@t2-qa.xkit.co\"}").
                when().post("https://qa1.learn.cisco/knowledgecenter/userpi/identityProvider/checkEmailUniqueness").
                then().statusCode(200).extract().response();

        String json = response.path("orgId");
        System.out.println(json);

    }

    @Test
    public void userCheck(){

        Response res = given().
                header("Authorization",createSsoToken()).
                header("Content-Type","application/json").
                body("{\"email\": \"cdeonara@cisco.com\"}").
                when().post("https://identitybts.webex.com/identity/config/v2/actions/EmailCheck/invoke").
                then().statusCode(200).extract().response();

        String json = res.body().asString();
        System.out.println(json);

        Headers allHeaders = res.getHeaders();
        List<String> headerValues = new ArrayList<String>();
        for(com.jayway.restassured.response.Header header : allHeaders) {
            headerValues.add(header.getName() + ":" + header.getValue());
            System.out.println(header.getName() + ":" + header.getValue());
        }

    }


    public void createinConsumer(String user){

        URI orgid = URI.create("https://identitybts.webex.com/identity/scim/consumer/v1/Users");
        System.out.println(orgid);

        String name = timeStamp(user) + "@mailinator.com";
        System.out.println("time stamped username is " + name);

        //Map<String,Object> mp = new Map
        Response res = given().
                headers("Accept","application/json; charset=UTF-8").contentType("application/json").
                headers("Authorization",createSsoToken()).
                body("{\n" +
                        " \"schemas\": [\n" +
                        "   \"urn:scim:schemas:core:1.0\",\n" +
                        "   \"urn:scim:schemas:extension:cisco:commonidentity:1.0\"\n" +
                        " ],\n" +
                        " \"userName\": \""+name+"\",\n" +
                        " \"organization\": \"744c204c-ebb4-4747-bdfa-ab3a7ea7de88\",\n" +
                        " \"department\": \"prof serv\"\n" +
                        "}").when().
                post(orgid).then().statusLine("HTTP/1.1 201 Created").
                extract().response();

        String json = res.body().asString();
        System.out.println(json);

        Headers allHeaders = res.getHeaders();
        List<String> headerValues = new ArrayList<String>();
        for(com.jayway.restassured.response.Header header : allHeaders) {
            headerValues.add(header.getName() + ":" + header.getValue());
            System.out.println(header.getName() + ":" + header.getValue());
        }

        arr.add(res.path("emails.value"));


    }


    public String timeStamp(String name) {
        String value = name + new SimpleDateFormat("ddMMYYhhmmss").format(new Date());
        System.out.println("CommonMethods:" +value);
        return value;

    }

    public void createCCI(String user, String password, String OrgId){

        URI orgid = URI.create("https://identitybts.webex.com/identity/scim/" + OrgId + "/v1/Users");
        System.out.println(orgid);

        String name = timeStamp(user) + "@st.ciscoknowledgesuite-poc.com";
        System.out.println("time stamped username is " + name);

        Response res = given().
              headers("Accept","application/json; charset=UTF-8").contentType("application/json").
                headers("Authorization",createSsoToken()).
                body("{\n" +
                        " \"schemas\": [\n" +
                        "   \"urn:scim:schemas:core:1.0\",\n" +
                        "   \"urn:scim:schemas:extension:cisco:commonidentity:1.0\"\n" +
                        " ],\n" +
                        " \"userName\": \""+name+"\",\n" +
                        " \"password\": \""+password+"\",\n" +
                        " \"organization\": \"744c204c-ebb4-4747-bdfa-ab3a7ea7de88\",\n" +
                        " \"department\": \"prof serv\"\n" +
                        "}").when().
                post(orgid).then().statusLine("HTTP/1.1 201 Created").
                extract().response();

        String json = res.body().asString();
        System.out.println(json);

        Headers allHeaders = res.getHeaders();
        List<String> headerValues = new ArrayList<String>();
        for(com.jayway.restassured.response.Header header : allHeaders) {
            headerValues.add(header.getName() + ":" + header.getValue());
            System.out.println(header.getName() + ":" + header.getValue());
        }

        arr.add(res.path("emails.value"));
    }


    @Test
    public void create() {
      /*  createCCI("t3", "Cisco@1234", "b90565f6-5182-4ef8-a35b-fc5ec97cfd00");
        createCCI("t2", "Cisco@1234", "744c204c-ebb4-4747-bdfa-ab3a7ea7de88");
        createCCI("t10", "Cisco@1234", "1a85d287-5c3c-4f78-bf76-0648a5c8cade");
        createCCI("dev", "Cisco@1234", "30369f76-aa96-4fc6-a8c2-5a186a50bff8");
        createCCI("qa2",  "Cisco@1234" , "b8d2cb55-8c54-43bd-bb13-9236f485715d");
        createCCI("t3", "Cisco@1234", "b90565f6-5182-4ef8-a35b-fc5ec97cfd00");
        createCCI("t2", "Cisco@1234", "744c204c-ebb4-4747-bdfa-ab3a7ea7de88");
        createCCI("t10", "Cisco@1234", "1a85d287-5c3c-4f78-bf76-0648a5c8cade");
        createCCI("dev", "Cisco@1234", "30369f76-aa96-4fc6-a8c2-5a186a50bff8");
        createCCI("qa2",  "Cisco@1234" , "b8d2cb55-8c54-43bd-bb13-9236f485715d");
        createinConsumer("con");
    */   // createinConsumer("con");
        createCCI("user_", "Cisco@000", "0bef1d72-3770-418e-b3e2-3a3ef77a087c");


       // createCCI("cte1@qa2.learn.cisco","Cisco@1234" , "d09fd759-eabf-4d3f-b7ab-e7f9bcd63769");


        for (Object value : arr) {
            System.out.println(value.toString());
        }


    }
}






