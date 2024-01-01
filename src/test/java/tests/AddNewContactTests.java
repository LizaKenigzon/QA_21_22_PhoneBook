package tests;

import models.Contact;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preConditions() {
        if (app.getHelperUser().isNotLogged()) {
            app.getHelperUser().login(new User().setEmail("test111@gmail.com").setPassword("Aa12345$"));
        }
    }

    @Test
    public void addNewContactSuccess(){
        Contact contact = Contact.builder()
                .name("Rita")
                .lastName("Abu Hanna")
                .phone("058-689-74-14")
                .email("abuhanna@mail.ru")
                .address("Haifa, Oz str. 5")
                .description("Kabala").build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
    }
}
