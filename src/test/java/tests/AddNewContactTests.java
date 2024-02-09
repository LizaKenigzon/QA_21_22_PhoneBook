package tests;

import manager.DataProviderContacts;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("mara@gmail.com").setPassword("Mmar123456$"));
        }

    }

    @Test(dataProvider = "contactCSV", dataProviderClass = DataProviderContacts.class)
    public void addContactSuccessAll(Contact contact) {
        int i = (int) (System.currentTimeMillis()) / 1000 % 3600;
//        Contact contact = Contact.builder()
//                .name("Tony"+i)
//                .lastName("Stark")
//                .address("NY")
//                .phone("323265932"+i)
//                .email("stark"+i +"@gmail.com")
//                .description("all fields")
//                .build();
        logger.info("Test run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-" + i + ".png");
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }

    @Test(groups = {"smoke","regress","retest"})
    public void addContactSuccessRequiredFields() {
        int i = (int) (System.currentTimeMillis()) / 1000 % 3600;
        Contact contact = Contact.builder()
                .name("TonyReq")
                .lastName("Stark")
                .address("NY")
                .phone("323265932" + i)
                .email("stark" + i + "@gmail.com")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-" + i + ".png");
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName("Stark")
                .address("NY")
                .phone("323265934562")
                .email("stark@gmail.com")
                .description("empty name")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongAddress() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("")
                .phone("32326593245452")
                .email("stark@gmail.com")
                .description("empty address")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongLastName() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .address("NY")
                .phone("323265934552")
                .email("stark@gmail.com")
                .description("empty last name")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test(dataProvider = "contactWrongPhone", dataProviderClass = DataProviderContacts.class)
    public void addNewContactWrongPhone(Contact contact) {
//        Contact contact = Contact.builder()
//                .name("Tony")
//                .lastName("Stark")
//                .address("NY")
//                .phone("")
//                .email("stark@gmail.com")
//                .description("empty phone")
//                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addNewContactWrongEmail() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("NY")
                .phone("3232659324521")
                .email("starkgmail.com")
                .description("wrong email")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));


    }

}
