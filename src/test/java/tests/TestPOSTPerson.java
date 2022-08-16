package tests;

import helpers.PersonServiceHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestPOSTPerson {
    private PersonServiceHelper personServiceHelper;

    @BeforeClass
    public void init(){
        personServiceHelper = new PersonServiceHelper();
    }

    @Test
    public void testPOSTCreatePerson(){
        String id = personServiceHelper.createPerson().jsonPath().getString("id");
        assertNotNull(id, "id is not null");
        String expedited = String.valueOf( personServiceHelper.getAllPersons().get(1).getId() );
        assertEquals(id,expedited);
    }
}
