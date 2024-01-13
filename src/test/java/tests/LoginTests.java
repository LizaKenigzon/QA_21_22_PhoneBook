package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        //if Sign out present --> Log out
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void loginSuccessWithUser(){
        logger.info("Start test with name `loginSuccessWithUser`");
        logger.info("Test data ---> email: 'test111@gmail.com' & password:'Aa12345$'");
        User user = new User().setEmail("test111@gmail.com").setPassword("Aa12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign out' present");
    }
    @Test
    public void loginSuccess(){
        logger.info("Start test with name `loginSuccess`");
        logger.info("Test data ---> email: 'test111@gmail.com' & password:'Aa12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("test111@gmail.com", "Aa12345$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign out' present");
    }

    //Assert.assertEquals();
    //Assert.assertNotEquals();
    //Assert.assertTrue();
    //Assert.assertFalse();
@Test
public void loginSuccessModel(){
    logger.info("Start test with name `loginSuccessModel`");
    logger.info("Test data ---> email: 'test111@gmail.com' & password:'Aa12345$'");
    app.getHelperUser().openLoginRegistrationForm();
    app.getHelperUser().fillLoginRegistrationForm("test111@gmail.com", "Aa12345$");
    app.getHelperUser().submitLogin();

    Assert.assertTrue(app.getHelperUser().isLogged());
    logger.info("Assert check is Element button 'Sign out' present");
}
    @Test
    public void loginWrongEmail(){
        logger.info("Start test with name `loginWrongEmail`");
        logger.info("Test data ---> email: 'test111gmail.com' & password:'Aa12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("test111gmail.com", "Aa12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert with error text 'Wrong email or password'");
    }
    @Test
    public void loginWrongPassword(){
        logger.info("Start test with name `loginWrongPassword`");
        logger.info("Test data ---> email: 'test111@gmail.com' & password:'Aa123'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("test111@gmail.com", "Aa123");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert with error text 'Wrong email or password'");
    }
    @Test
    public void loginUnregisteredUser(){
        logger.info("Start test with name `loginUnregisteredUser`");
        logger.info("Test data ---> email: 'luck111@gmail.com' & password:'Aa12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("luck111@gmail.com", "Aa12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert with error text 'Wrong email or password'");
    }
}
