package tests;

import models.Contact;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preConditions() {
        //if(!app.getHelperUser().isLogged()) --> ! = NOT
        if (app.getHelperUser().isNotLogged()) {
            app.getHelperUser().login(new User().setEmail("test111@gmail.com").setPassword("Aa12345$"));
        }
    }

    @Test
    public void addNewContactSuccess(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        Contact contact = Contact.builder()
                .name("Rita")
                .lastName("Abu Hanna")
                .phone("058689"+i)
                .email("abuhanna"+i+"@mail.ru")
                .address("Haifa, Oz str. 5")
                .description("Kabala")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        Contact contact = Contact.builder()
                .name("Rita")
                .lastName("Abu Hanna")
                .phone("058689"+i)
                .email("abuhanna"+i+"@mail.ru")
                .address("Haifa, Oz str. 5")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
    }

    @Test
    public void addNewContactWrongName(){

    }

    @Test
    public void addNewContactWrongAddress(){

    }

    @Test
    public void addNewContactWrongLastName(){

    }

    @Test
    public void addNewContactWrongPhone(){

    }

    @Test
    public void addNewContactWrongEmail(){

    }

    //@AfterClass

}
