package tests;

import helpers.PersonServiceHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import models.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestGETPerson {

    private PersonServiceHelper personServiceHelper;

    @BeforeClass
    public void init(){
        personServiceHelper = new PersonServiceHelper();
    }

    @Test
    public void testGetAllPerson(){
        List<Person> personList = personServiceHelper.getAllPersons();
        assertNotNull(personList, "Person List is not Empty");

    }

    @Test
    public void testGetPerson(){
        Response response = personServiceHelper.getPerson(1);

        ResponseBody body = response.getBody();
        System.out.println(body.asString());

        JsonPath jsonPath = new JsonPath(body.asInputStream());
        int user_id = jsonPath.getInt("id");
        assertEquals(user_id,1);

    }
}
