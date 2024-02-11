package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        //if Sign out present --> Log out
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }
    @Test
    public void registrationSuccess(){
        logger.info("Test data ---> email:'test+i+@gmail.com' & password:'Aa12345$'");
        Random random = new Random();
        int i = random.nextInt(1000);
        //int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = new User().setEmail("test"+i+"@gmail.com").setPassword("Aa12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
        logger.info("Assert check is Element button 'Sign out' present");
        logger.info("Assert check is No Contacts Here Displayed");
    }

    @Test//(description = "Bug report #1234", enabled = false)
    //after --> (description = "Bug report #1234", Fixed)
    public void registrationWrongEmail(){
        logger.info("Test data ---> email:'testgmail.com' & password:'Aa12345$'");
        User user = new User().setEmail("testgmail.com").setPassword("Aa12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        logger.info("Assert check is Alert Present 'Wrong email or password format'");
        //isAlertPresent with .contains
    }

    @Test
    public void registrationWrongPassword(){
        logger.info("Test data ---> email:'test+i+@gmail.com' & password:'Aa12'");
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().setEmail("test"+i+"@gmail.com").setPassword("Aa12");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        logger.info("Assert check is Alert Present 'Wrong email or password format'");
    }

    @Test
    public void registrationExistUser(){
        logger.info("Test data ---> email: 'test111@gmail.com' & password:'Aa12x8752622!'");
        User user = new User().setEmail("test111@gmail.com").setPassword("Aa12x8752622!");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
        //isAlertPresent with .contains
        logger.info("Assert check is Alert Present 'User already exist'");
    }

}
