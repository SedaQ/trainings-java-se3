package com.trainings.xml.xpath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Pavel Seda
 */
public class XPathSeleniumExample {

    public static void main(String[] args) {
        XPathSeleniumExample xPathExample = new XPathSeleniumExample();
        xPathExample.logInFacebook();
    }

    private void logInFacebook() {
        // https://chromedriver.storage.googleapis.com/index.html?path=2.42/ - driver for Selenium

        System.setProperty("webdriver.chrome.driver", "./jprog2-xml/src/main/resources/chrome-driver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://facebook.com");

        WebElement username = driver.findElement(By.id("email"));
        username.sendKeys("java-trainings@email.cz");

        WebElement password = driver.findElement(By.id("pass"));
        password.sendKeys("JavaTrainings");

        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        loginButton.click();

        //WebDriverWait wait = new WebDriverWait(driver, 180);
        //wait.until(ExpectedConditions.textMatches(By.xpath("//span[@class='routeDistance']"), Pattern.compile("\\w+")));
    }
}
