package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

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
        int i = (int)(System.currentTimeMillis()/10000%3600);
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
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        app.getHelperContact().submitContact();
        Assert.assertEquals(app.getHelperUser().getPhoneFromContact(), contact.getPhone()+"");
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        int i = (int)(System.currentTimeMillis()/10000%3600);
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
        Assert.assertEquals(app.getHelperUser().getPhoneFromContact(), contact.getPhone()+"");
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Abu Hanna")
                .phone("05868958945")
                .email("abuhanna@mail.ru")
                .address("Haifa, Oz str. 5")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertFalse(app.getHelperUser().isElementPresent(By.cssSelector(".contact-page_leftdiv__yhyke")));
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongAddress(){
        int i = (int)(System.currentTimeMillis()/10000%3600);
        Contact contact = Contact.builder()
                .name("Masha")
                .lastName("Abu Hanna")
                .phone("058689"+i)
                .email("abuhanna"+i+"@mail.ru")
                .address("")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertFalse(app.getHelperUser().isElementPresent(By.cssSelector(".contact-page_leftdiv__yhyke")));
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongLastName(){
        int i = (int)(System.currentTimeMillis()/10000%3600);
        Contact contact = Contact.builder()
                .name("Anna")
                .lastName("")
                .phone("058689892142")
                .email("abuhanna15@mail.ru")
                .address("Haifa, Oz str. 5")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertFalse(app.getHelperUser().isElementPresent(By.cssSelector(".contact-page_leftdiv__yhyke")));
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongPhone(){
        int i = (int)(System.currentTimeMillis()/10000%3600);
        Contact contact = Contact.builder()
                .name("Anna")
                .lastName("Anna")
                .phone("")
                .email("abuhanna"+i+"@mail.ru")
                .address("Haifa, Oz str. 5")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    //@Test = BUG
    //public void addNewContactWithoutEmail(){
    //    int i = (int)(System.currentTimeMillis()/1000%3600);
    //    Contact contact = Contact.builder()
    //          .name("Anna")
    //            .lastName("Anna")
    //            .phone("58963247891")
    //            .email("")
    //            .address("Haifa, Oz str. 5")
    //            .build();
    //    app.getHelperContact().openContactForm();
    //    app.getHelperContact().fillContactForm(contact);
    //    app.getHelperContact().submitContact();
    //   Assert.assertEquals(app.getHelperUser().getPhoneFromContact(), contact.getPhone()+"");
    //}
    @Test
    public void addNewContactWrongEmail(){
        int i = (int)(System.currentTimeMillis()/10000%3600);
        Contact contact = Contact.builder()
                .name("Anna")
                .lastName("Anna")
                .phone("58963247891")
                .email("mamamail.ru")
                .address("Haifa, Oz str. 5")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid:"));
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

//    @AfterMethod
//    public void postConditions(){
//        app.getHelperContact().returnToHome();
//    }

}
