package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import constants.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Person;
import org.apache.http.HttpStatus;
import utils.ConfigManager;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Type;
import java.util.List;

public class PersonServiceHelper {

    // Read config variables
    //Make a GET Request to url and send data to TestGETPerson

    public static final String BASE_URL = ConfigManager.getInstance().getString("base_url");
    public static final String PORT = ConfigManager.getInstance().getString("port");

    public PersonServiceHelper(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = Integer.parseInt(PORT);
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<Person> getAllPersons(){
        Response response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .get(EndPoints.GET_ALL_PERSONS)
                .andReturn();

        Type type = new TypeReference<List<Person>>(){}.getType();
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "OK");

        List<Person> personList = response.as(type);
        return personList;
    }

    public Response createPerson(){

        Person person = new Person();
        person.setFirstName("David");
        person.setLastName("Miclea");
        person.setId(10);
        person.setAge(28);
        person.setPhoneNumbers("+40729516638");
        person.setAddress("Honolulu");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(person)
                .post(EndPoints.CREATE_PERSON)
                .andReturn();

        assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Created");

        return response;
    }
}
