package com.evertix.sessionservice;

import com.evertix.sessionservice.entities.Session;
import com.evertix.sessionservice.util.RestPageImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;

@CucumberContextConfiguration
@SpringBootTest(classes = SessionServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
public class SessionManageStepDefs {

    private URL base;
    private String username;
    @Autowired
    protected TestRestTemplate template;

    private ResponseEntity<RestPageImpl<Session>> listResponseEntity;

    @Before
    public void setUp() throws MalformedURLException {
        this.base=new URL( "http://tutofast-session-service.eastus.azurecontainer.io:8088/api/sessions/page");
    }

    @Given("Student with a username {string}")
    public void studentWithAUsername(String arg0) {
        this.username=arg0;
    }

    @When("go to session option and call to api is made")
    public void goToSessionOptionAndCallToApiIsMade() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ParameterizedTypeReference<RestPageImpl<Session>> responseType = new ParameterizedTypeReference<RestPageImpl<Session>>() { };
        this.listResponseEntity = template.exchange(base.toString(), HttpMethod.GET,request, responseType);
    }

    @Then("response status is {int}")
    public void responseStatusIs(int arg0) {
        Assert.assertEquals(listResponseEntity.getStatusCodeValue(),arg0,listResponseEntity.getStatusCodeValue());
    }

    @And("all session data is retrieved")
    public void allSessionDataIsRetrieved() {
        Assert.assertEquals("*************Size is "+listResponseEntity.getBody().getTotalElements(),1,listResponseEntity.getBody().getTotalElements());
    }

    @Given("Teacher with a username {string}")
    public void teacherWithAUsername(String arg0) {
        this.username=arg0;
    }

    @Given("Admin with username {string}")
    public void adminWithUsername(String arg0) {
        this.username=arg0;
    }
}
