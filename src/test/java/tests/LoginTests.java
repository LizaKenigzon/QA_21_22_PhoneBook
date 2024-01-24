package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import manager.DataProviderUser;

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
    @Test(dataProvider ="loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
        logger.info("Start test with name `loginSuccess`");
        logger.info("Test data ---> email: "+ email +" & password: "+password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign out' present");
    }

    //Assert.assertEquals();
    //Assert.assertNotEquals();
    //Assert.assertTrue();
    //Assert.assertFalse();

    @Test(dataProvider = "loginFile", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelDP(User user){
        logger.info("Start test with name `loginSuccessModelDP`");
        logger.info("Test data ---> "+ user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign out' present");
    }
    @Test(dataProvider = "loginModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){
    logger.info("Start test with name `loginSuccessModel`");
    logger.info("Test data ---> "+ user.toString());
    app.getHelperUser().openLoginRegistrationForm();
    app.getHelperUser().fillLoginRegistrationForm(user);
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
