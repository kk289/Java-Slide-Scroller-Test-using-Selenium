package Classwork;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class sliderScrollVerify {

    WebDriver wd;

    @Before
    // Open ChromeBrowser
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // chrome browser
        wd = new ChromeDriver();
        wd.get("http://automationpractice.com/index.php?id_category=3&controller=category");
    }

    @Test
    public void priceSlider() throws InterruptedException{
        // Scroll down
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(2000);

        int target = 53;
        int method = 2;  // method controller
        WebElement slide = wd.findElement(By.xpath("//*[@id='layered_price_slider']/a[1]"));
        Actions move = new Actions(wd);
        if (method == 1){
            for (int i = 1; i <= target; i++){
                // Slider.sendKeys(Keys.ARROW_RIGHT)
                Action action = move.dragAndDropBy(slide, i, 0).build();
                action.perform();
            }
        }
        if (method == 2){
            move.dragAndDropBy(slide,300,0).build().perform();
        }
        if (method == 3){
            move.moveToElement(slide).clickAndHold().moveToElement(slide, 300, 0).release().perform();
        }
        if (method == 4){
            js.executeScript("arguments[0].setAttribute('style', 'left: 100%;')", slide);
        }

        Thread.sleep(3000);  // verify the level is 53

        // Assertion
        Assert.assertEquals(wd.findElement(By.id("layered_price_range")).getText(), "$53.00 - $53.00");
    }

    @After
    // Close the browser
    public void Close() throws InterruptedException {
        Thread.sleep(2000);
        wd.quit();
    }
}
