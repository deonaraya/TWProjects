package com.deo.tw.api;

import com.deo.tw.pages.LeftNavPage;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by chandrad on 7/3/16.
 */
public class moodleTest {
    private static final String PUBLIC_KEY_FILE = "public.pem";
    Map<String,String> mp = new HashMap<String, String>();

    WebDriver driver = new FirefoxDriver();
    //WebDriver driver = new ChromeDriver();
    String authtoken = null ;
    String cookieString = null;
    String xssTag = null;
    String groupName = null;
    String groupId = null;
    String email = null;
    String apiAuthtoken = null;

  /*  @FindBys(@FindBy(xpath = "//li[@class='active']/button/span"))
    private List<WebElement> parents ;

    @FindBys(@FindBy(xpath = "//ul[@class='submenu']//a"))
    private List<WebElement> childs ;
 */


    @BeforeClass
    public void setUp() throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, KeyStoreException, InvalidKeySpecException, IllegalBlockSizeException {
        driver.get("https://qa1.learn.cisco/");
        //driver.get("https://t10-qa.xkit.co");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int Width = (int) toolkit.getScreenSize().getWidth();
        int Height = (int) toolkit.getScreenSize().getHeight();
        driver.manage().window().setSize(new Dimension(Width, Height));
    }

    @Test
    public void newtests() throws InterruptedException {
        signIn("deonaraya1@gmail.com", "Cisco@1234");
        LeftNavPage nav = new LeftNavPage(driver);
        nav.navToTrainingCatalog().filterAndAssert().validateContent();
    }

    @Test
    public void checkTests(){
        signIn("deonaraya1@gmail.com", "Cisco@1234");
        LeftNavPage nav = new LeftNavPage(driver);

        nav.navToKnowledgeLibrary();
        nav.navToSharedLearning();
        nav.navToUser();
        nav.navToTrainingCatalog().validateContent();
        nav.navToMyFiles();
        System.out.println("navigated to my files page");

    }

    @Test
    public void printHref(){
        signIn("deonaraya1@gmail.com", "Cisco@1234");

        List<WebElement> parents = driver.findElements(By.xpath("//li[@class='active']/button/span"));
        List<WebElement> childs = driver.findElements(By.xpath("//ul[@class='submenu']//a"));

        for (WebElement f: parents) {
            System.out.println(f.getText());
            f.click();
            for (WebElement e: childs)
            {
             System.out.println(e.getText());
            }

        }
    }

//    @Test
//    public void getToken() throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, KeyStoreException, IllegalBlockSizeException {
//        signIn("deonaraya1@gmail.com", "Cisco@1234");
//        Set<Cookie> cookies = driver.manage().getCookies();
//
//        authtoken = driver.manage().getCookieNamed("auth-token").getValue();
//        String tenantToken = driver.manage().getCookieNamed("tenant-token").getValue();
//        String shortName = driver.manage().getCookieNamed("shortName").getValue();
//        String username = driver.manage().getCookieNamed("username").getValue();
//        String xssDigest = driver.manage().getCookieNamed("xss-digest").getValue();
//        cookieString = "auth-token=" + authtoken + "; tenant-token=" + tenantToken + "; shortName=" + shortName + "; username=" + username + "; xss-digest=" + xssDigest ;
//        xssTag = new RSAencrypt().encrypt(DigestUtils.sha1Hex(authtoken), PUBLIC_KEY_FILE);
//    }

    public void getToken(String username,String password) throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, KeyStoreException, IllegalBlockSizeException {
        signIn(username,password);
        authtoken = driver.manage().getCookieNamed("auth-token").getValue();
        String tenantToken = driver.manage().getCookieNamed("tenant-token").getValue();
        String shortName = driver.manage().getCookieNamed("shortName").getValue();
        String name = driver.manage().getCookieNamed("username").getValue();
        String xssDigest = driver.manage().getCookieNamed("xss-digest").getValue();
        cookieString = "auth-token=" + authtoken + "; tenant-token=" + tenantToken + "; shortName=" + shortName + "; username=" + name + "; xss-digest=" + xssDigest ;
        xssTag = new RSAencrypt().encrypt(DigestUtils.sha1Hex(authtoken), PUBLIC_KEY_FILE);
    }

    @Test
    public void createGroup() throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, KeyStoreException, InvalidKeySpecException, IllegalBlockSizeException {

        getToken("deonaraya1@gmail.com","Cisco@1234");
        groupName = "Category" + new SimpleDateFormat("hhmmss").format(new Date());

        Response res = given().header("content-type", "application/json").header("Cookie", cookieString)
                .header("XSS-Tag",xssTag).
                        body("{\"showUserGroups\":true,\"name\":\""+groupName+"\",\"description\":\"course/courses\",\"visibility\":false,\"errors\":\"\",\"validationErrors\":{}}").
                        post("https://qa1.learn.cisco/knowledgecenter/tenantmanagement/usergroup").then().statusCode(201).extract().response();

        String json = res.body().asString();
        System.out.println(json);

        JsonPath jsonPath = new JsonPath(json);
        groupId = jsonPath.getString("id");
        System.out.println(groupId);

    }

    public void associateGroup(String groupId, String email){

        URI groupAssociate = URI.create("https://qa1.learn.cisco/knowledgecenter/tenantmanagement/usergroup/" + groupId + "/members");

        System.out.println(groupAssociate);
        Response res1 = given().header("content-type", "application/json").header("Cookie", cookieString)
                .header("XSS-Tag",xssTag).
                body("{\"addedMembers\":[\""+email+"\"],\"removedMembers\":[]}")
                .put(groupAssociate).
                        then().statusCode(200).extract().response();

        String json1 = res1.body().asString();
        System.out.println(json1);
    }

    public void createUser(String username,String OrgId ){

        String serviceUserId = "cisco-knowledge.svcAccount";
        String servicePassword = "56juWFWWIgnSf..Ggkjeiu8.2lo0976uDFF44Ma452016" ;

        Response res = given().
                        header("Content-Type", "application/x-www-form-urlencoded").
                        body("username="+ serviceUserId + "&password="+ servicePassword + "&uri=realm%3D%2Fcommon_identity_operations%26module%3DserviceAccounts").
                        when().
                        post("https://idbrokerbts.webex.com/idb/identity/authenticate").then().
                        extract().response();

        String tokenId = res.body().asString() ;
        String ssoToken = "ssoToken" + tokenId.substring(9,tokenId.length()-1);
        System.out.println(ssoToken);

        URI userCreate = URI.create("https://identitybts.webex.com/identity/scim/" + OrgId + "/v1/Users");
        System.out.println(userCreate);

        email =  username + new SimpleDateFormat("hhmmss").format(new Date()) + "@mailinator.com";
        System.out.println("time stamped username is " + email);

        Response res1 = given().
                headers("Accept","application/json; charset=UTF-8").contentType("application/json").
                headers("Authorization",ssoToken).
                body("{\n" +
                        " \"schemas\": [\n" +
                        "   \"urn:scim:schemas:core:1.0\",\n" +
                        "   \"urn:scim:schemas:extension:cisco:commonidentity:1.0\"\n" +
                        " ],\n" +
                        " \"userName\": \""+email+"\",\n" +
                        " \"password\": \"Cisco@123\",\n" +
                        " \"name\": {\n" +
                        "   \"givenName\": \"abc\"\n" +
                        " }\n" +
                        "}").when().
                post(userCreate).then().statusLine("HTTP/1.1 201 Created").
                extract().response();

        String json = res1.body().asString();
        System.out.println(json);

        Headers allHeaders = res.getHeaders();
        List<String> headerValues = new ArrayList<String>();
        for(com.jayway.restassured.response.Header header : allHeaders) {
            headerValues.add(header.getName() + ":" + header.getValue());
            System.out.println(header.getName() + ":" + header.getValue());
        }

    }

    public void csvOnboardUser(String clksBaseUrl, String clksBasePath, String username1) throws IOException {
        Response res1 =  given().header("content-type", "multipart/form-data").header("Cookie", cookieString)
                .header("XSS-Tag",xssTag)
                .multiPart("file", new File(getCSVFileForUserUpload(username1)))
                .when()
                .post(clksBaseUrl + "/" + clksBasePath + "/userpi/admin/users/csv_upload")
                .then()
                .statusCode(202)
                .extract()
                .response();

        String json1 = res1.body().asString();
        System.out.println(json1);

    }

    private String getCSVFileForUserUpload(String username1) throws IOException {
        String header = "Username,First Name,Last Name,Address,City,Country Code,Title,Organization,Manager,Active,CompanyName,PartnerName,IDPId";
        String user1 = username1 + ",V,2,DummyLocation,Chennai,IN,Dev,DummyOrg,,,,,";
        String fileName = "user.csv";

        File f = new File(fileName);

        try(
                FileOutputStream fo = new FileOutputStream(f);
                PrintStream ps = new PrintStream(fo);
        ) {
            ps.println(header);
            ps.println(user1);
            return f.getAbsolutePath();
        }
    }

    @Test
    public void runtoTest() throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, KeyStoreException, IllegalBlockSizeException {

        createGroup();
        createUser("larkin","744c204c-ebb4-4747-bdfa-ab3a7ea7de88");
        csvOnboardUser("https://qa1.learn.cisco", "knowledgecenter", email);
        associateGroup(groupId,email);
//        getApiToken();
//        createElearningCourse();
//        createILTCourse();
//        createThirdPartyCourse();
//        createWebexCourse();


    }

    public void getApiToken(){
        signIn("cte1@qa5.learn.cisco", "Cisco@1234");
        apiAuthtoken = driver.manage().getCookieNamed("auth-token").getValue();

        System.out.print("integration token is " + apiAuthtoken);
    }


    @Test
    public void createElearningCourse() {


        getApiToken();

        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get("/Users/chandrad/IdeaProjects/apiSuite/src/main/resources/elearning.json")), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String uniqOrigId = String.valueOf(System.currentTimeMillis());
        String shortName = "Generic Course api created" + uniqOrigId;
        String uniqOrigSessionId = String.valueOf(System.currentTimeMillis());

        String inputJson = String.format(content, uniqOrigId, shortName, uniqOrigSessionId);

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(inputJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) obj;
        Response res = given().
                headers("Accept", "application/json; charset=UTF-8").contentType("application/json").
                headers("IntegrationToken", apiAuthtoken).
                headers("authToken", apiAuthtoken).
                body(jsonObject).post("https://qa1.learn.cisco/knowledgecenter/ka/lms/course").then().statusCode(401).extract().response();

        String json = res.body().asString();
        System.out.println(json);

    }

    @Test
    public void createILTCourse() {
//        Set<Cookie> cookies = driver.manage().getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println(cookie);
//        }
//        String apiAuthtoken = driver.manage().getCookieNamed("auth-token").getValue();

        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get("/Users/chandrad/IdeaProjects/apiSuite/src/main/resources/ilt.json")), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String uniqOrigId = String.valueOf(System.currentTimeMillis());
        String shortName = "ILT-Comcast-WebEx" + uniqOrigId;
        String uniqOrigSessionId = String.valueOf(System.currentTimeMillis());

        String inputJson = String.format(content, uniqOrigId, shortName, uniqOrigSessionId);

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(inputJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) obj;
        Response res = given().
                headers("Accept", "application/json; charset=UTF-8").contentType("application/json").
                headers("IntegrationToken", apiAuthtoken).
                headers("authToken", apiAuthtoken).
                body(jsonObject).post("https://t10-qa.xkit.co/knowledgecenter/ka/lms/course").then().statusCode(200).extract().response();

        String json = res.body().asString();
        System.out.println(json);

    }

    @Test
    public void createThirdPartyCourse() {
//        Set<Cookie> cookies = driver.manage().getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println(cookie);
//        }
//        String apiAuthtoken = driver.manage().getCookieNamed("auth-token").getValue();

        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get("/Users/chandrad/IdeaProjects/apiSuite/src/main/resources/thirdPartyCourse.json")), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String uniqOrigId = String.valueOf(System.currentTimeMillis());
        String shortName = "3PC-Comcast-WebEx" + uniqOrigId;
        String uniqOrigSessionId = String.valueOf(System.currentTimeMillis());

        String inputJson = String.format(content, uniqOrigId, shortName, uniqOrigSessionId);

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(inputJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) obj;
        Response res = given().
                headers("Accept", "application/json; charset=UTF-8").contentType("application/json").
                headers("IntegrationToken", apiAuthtoken).
                headers("authToken", apiAuthtoken).
                body(jsonObject).post("https://t10-qa.xkit.co/knowledgecenter/ka/lms/course").then().statusCode(200).extract().response();

        String json = res.body().asString();
        System.out.println(json);

    }

    @Test
    public void createWebexCourse() {
//        Set<Cookie> cookies = driver.manage().getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println(cookie);
//        }
//        String apiAuthtoken = driver.manage().getCookieNamed("auth-token").getValue();

        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get("/Users/chandrad/IdeaProjects/apiSuite/src/main/resources/webex.json")), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String uniqOrigId = String.valueOf(System.currentTimeMillis());
        String shortName = "WBT-Comcast-WebEx" + uniqOrigId;
        String uniqOrigSessionId = String.valueOf(System.currentTimeMillis());

        String inputJson = String.format(content, uniqOrigId, shortName, uniqOrigSessionId);

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(inputJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) obj;
        Response res = given().
                headers("Accept", "application/json; charset=UTF-8").contentType("application/json").
                headers("IntegrationToken", apiAuthtoken).
                headers("authToken", apiAuthtoken).
                body(jsonObject).post("https://t10-qa.xkit.co/knowledgecenter/ka/lms/course").then().statusCode(200).extract().response();

        String json = res.body().asString();
        System.out.println(json);

    }

    @Test
    public void createCoursesType(){
        createElearningCourse();
        createWebexCourse();
        createThirdPartyCourse();
        createILTCourse();



    }

    @Test
    public void signIn(String username, String password){
        WebElement a = new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("msg_page-box")));
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        WebElement c = new WebDriverWait(driver,25).until(ExpectedConditions.presenceOfElementLocated(By.className("brandingLoginForm")));
        driver.findElement(By.id("IDToken2")).sendKeys(password);
        driver.findElement(By.name("Login.Submit")).click();
//        WebElement b = new WebDriverWait(driver,90).until(ExpectedConditions.presenceOfElementLocated(By.className("user-info")));
//        System.out.println(b.getText());

        WebDriverWait  waitTime = new WebDriverWait(driver,60);
        waitTime.until(new ExpectedCondition<Boolean>()
        {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean)js.executeScript("return jQuery.active == 0");
            }});

//        if (driver.findElement(By.xpath("//div[@id='termsAndConditions']//h4[@class='modal-title']")).isDisplayed())
//        {  driver.findElement(By.xpath("//div[@id='termsAndConditions']//input")).click();
//            driver.findElement(By.xpath("//div[@id='termsAndConditions']//button[text()='Continue']")).click();}
//        else
//        System.out.println("TNC do not appear");

    }








}
