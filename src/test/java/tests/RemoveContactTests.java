package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preConditions() {
        //if(!app.getHelperUser().isLogged()) --> ! = NOT
        if (app.getHelperUser().isNotLogged()) {
            app.getHelperUser().login(new User().setEmail("test111@gmail.com").setPassword("Aa12345$"));
        }
        //if list<3 --> add 3 contacts
        app.getHelperContact().provideContacts();
    }

    @Test
    public void removeOneContact(){
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
        //Assert size list less by one
    }
    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertEquals(app.getHelperContact().getMessage(), "No Contacts here!");
    }
}
