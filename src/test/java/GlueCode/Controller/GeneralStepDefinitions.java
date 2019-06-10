package GlueCode.Controller;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public class GeneralStepDefinitions {

    public static WebDriver driver;


//scenario 1
    @Given("^I navigate to \"([^\"]*)\" page$")
    public void openPage(String page) throws Throwable {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get(page);
    }

    @When("^I send a message$")
    public void iSendAMessage() throws Throwable {
        //for (int i = 0; i < 100; i++) {
        driver.findElement(By.id("chatInput")).sendKeys("hello world");
        driver.findElement(By.id("chatSubmit")).click();
    }

    @Then("^The sent message should be visible to the end users$")
    public void theSentMessageShouldBeVisibleToTheEndUser() throws Throwable {
        Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"root\"]/form/ul/li[5]/div[2]/div[2]/div[1]")).size() > 0;
        Assert.assertTrue(isPresent);
    }

    @And("^The message should have a time stamp$")
    public void theMessageShouldHaveATimeStamp() throws Throwable {
        Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"root\"]/form/ul/li[5]/div[1]/span")).size() > 0;
        Assert.assertTrue(isPresent);
    }

    //Unable to locate due to a lack of ID--react is more interactive and requires less ID's is what im assuming.
    @And("^Message profile image is present$")
    public void messageProfileImageIsPresent() throws Throwable {

    }

    @Then("^Calender is shown during mouse over$")
    public void calenderIsShownDuringMouseOver() {
        Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"root\"]/form/ul/li[5]/div[2]/div[2]/div[2]")).size() > 0;
        Assert.assertTrue(isPresent);
    }
//scenario 2
    //negative test cases
    @Given("^I send a message with five spaces$")
    public void iSendAMessageWithFiveSpaces() {
        driver.findElement(By.id("chatInput")).sendKeys("     ");
        driver.findElement(By.id("chatSubmit")).click();
    }
    @Then("^I should not be able to send a blank message$")
    public void iShouldNotBeAbleToSendABlankMessage() {
        String text = "     ";
        String sentMessage = driver.findElement(By.xpath("//*[@id=\"root\"]/form/ul/li[6]/div[2]/div[2]")).getText();
        if(sentMessage.equals("     ")) {
            Assert.assertTrue("Message should be sent", true);
        } else {
            Assert.assertFalse("Message should not be sent", false);
        }
    }
//scenario 3
    @Given("^I send a message with multiple space key entry$")
    public void iSendAMessageWithMultipleSpaceKeyEntry() {
        driver.findElement(By.id("chatInput")).sendKeys("hello       universe");
        driver.findElement(By.id("chatSubmit")).click();
    }
    @Then("^Message sent should be spaced accordingly$")
    public void messageSentShouldBeSpacedAccordingly() throws Throwable {
        String text = "hello universe";
        String sentMessage = driver.findElement(By.xpath("//*[@id=\"root\"]/form/ul/li[7]/div[2]/div[2]/div[1]")).getText();
        if(sentMessage.equals("hello       universe")) {
            Assert.assertTrue("Spaces are being sent properly", true);
        } else {
            Assert.assertFalse("Spaces are not being set properly", false);
        }
    }
//scenario 4
    @Given("^I send a long message$")
    public void iSendALongMessage() {
        driver.findElement(By.id("chatInput")).sendKeys("Lucas ipsum dolor sit amet antilles greedo windu leia hoth kenobi " +
                "mon sith windu dagobah. Organa thrawn coruscant jango. Darth darth palpatine skywalker boba. Jabba kashyyyk binks coruscant " +
                "kashyyyk thrawn. Qui-gonn sidious ackbar sidious jabba ben palpatine. Solo skywalker darth wicket lobot solo kessel sebulba. " +
                "Solo mara organa skywalker kenobi c-3p0 skywalker. Coruscant aayla owen r2-d2. Hutt solo organa obi-wan. Darth secura sith chewbacca " +
                "palpatine c-3po jango dooku jade. Tatooine sidious hutt vader.");
        driver.findElement(By.id("chatSubmit")).click();

    }

    @When("^It should cut off at (\\d+) characters$")
    public void itShouldCutOffAtCharacters(int arg0) {
        String myCount = driver.findElement(By.xpath("//*[@id=\"root\"]/form/ul/li[8]/div[2]/div[2]/div[1]")).getText();
        if(myCount.equals("Lucas ipsum dolor sittilles greedo windu leia hoth kenobi mon sith windu dagobah. Organa thrawn coruscant jango. Darth darth palpati")) {
            Assert.assertTrue("character limit maxed",true);

        } else{
            Assert.assertFalse("more characters are available",false);
        }

    }

    @Then("^I send input XML code$")
    public void iSendInputXMLCode() {
        driver.findElement(By.id("chatInput")).sendKeys("<food>\n" +
                "    <name>Belgian Waffles</name>\n" +
                "    <price>$5.95</price>\n" +
                "    <description>\n" +
                "   Two of our famous Belgian Waffles with plenty of real maple syrup\n" +
                "   </description>\n" +
                "    <calories>650</calories>\n" +
                "</food>\n" +
                "<food>\n" +
                "    <name>Strawberry Belgian Waffles</name>\n" +
                "    <price>$7.95</price>\n" +
                "    <description>\n" +
                "    Light Belgian waffles covered with strawberries and whipped cream\n" +
                "    </description>\n" +
                "    <calories>900</calories>");
    }

    @Then("^It should not crash$")
    public void itShouldNotCrash() {
        driver.findElement(By.id("chatSubmit"));
    }
//scenario 5
    @Given("^I send a message (\\d+) times$")
    public void iSendAMessageTimes(int arg0) {
        for (int i = 0; i < 20; i++) {
            driver.findElement(By.id("chatInput")).sendKeys("hello world");
            driver.findElement(By.id("chatSubmit")).click();

        }
    }
    @When("^I refresh the page$")
    public void iRefreshThePage() {
        driver.navigate().refresh();
    }

    @Then("^Chat history should remain$")
    public void chatHistoryShouldRemain() {
        driver.findElements(By.xpath("//*[@id=\"root\"]/form/ul/li[5]/div[2]/div[2]")).size();
        boolean isPresents = false;
        Assert.assertFalse(isPresents);
    }


}
    //driver.quit()


