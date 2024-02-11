package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        //pause(1000);
        click(By.xpath("//a[text()='ADD']"));
    }

    public void fillContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());
    }

    public void submitContact() {
        click(By.xpath("//b[text()='Save']"));
    }

    public void returnToHome() {
        click(By.xpath("//a[text()='CONTACTS']"));
        click(By.xpath("//a[text()='ADD']"));
    }

    public boolean isContactAddedByName(String name)
    {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement el:list)
        {
            if(el.getText().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement el:list)
        {
            if(el.getText().equals(phone))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of Contacts list before remove is -->" + before);
        removeContact();
        int after = countOfContacts();
        logger.info("Number of Contacts list after remove is -->" + after);
        return before-after;
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(5000);
    }

    private int countOfContacts() {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        int res = list.size();
        return res;
    }

    public void removeAllContacts(){
        while(wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() != 0){
            removeContact();
        }
    }

    public void provideContacts() {
        if(countOfContacts()<3){
            for(int i=0; i<3; i++){
                addOneContact();
            }
        }
    }

    private void addOneContact() {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Harry"+i)
                .lastName("Potter"+i)
                .address("Hogwards"+i)
                .email("harry"+i+"@gmail.com")
                .phone("558895"+i)
                .description("Friend")
                .build();
        openContactForm();
        fillContactForm(contact);
        submitContact();
        pause(1500);
    }


}