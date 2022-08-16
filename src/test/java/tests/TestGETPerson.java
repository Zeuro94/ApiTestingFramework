package tests;

import helpers.PersonServiceHelper;
import models.Person;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

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
}
