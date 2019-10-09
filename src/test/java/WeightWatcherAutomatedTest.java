import static org.junit.Assert.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeightWatcherAutomatedTest {

  static WebDriver driver;

  @BeforeClass
  public static void BrowserOpen()

  {
    //WebDriver driver = null;
    WebDriverManager.chromedriver().version("77.0.3865.40").setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    driver = new ChromeDriver(options);
    driver.get("https://www.weightwatchers.com/us/");
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @AfterClass
  public static void BrowserClose() {
    driver.quit();
  }

  @Test
  public void Test1() {

    assertEquals("WW (Weight Watchers): Weight Loss & Wellness Help", driver.getTitle());

    driver.findElement(By.className("find-a-meeting")).click();

    new WebDriverWait(driver, 10).until(
        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
            .equals("complete"));

    assertEquals("Find WW Studios & Meetings Near You | WW USA", driver.getTitle());  // validating title

    driver.findElement(By.id("meetingSearch")).sendKeys("10011");

    driver.findElement(By.cssSelector("button.btn.spice-translated")).click();  // searching for location

    WebElement firstRepeaterElem = driver.findElements(By.className("meeting-locations-list"))
        .get(0).findElement(By.id("ml-1180510"));

    String title = firstRepeaterElem.findElement(By.xpath("//span[@ng-if='!linkName']")).getText();
    System.out.println(title); // printing location if first search item

    String distance = firstRepeaterElem.findElement(By.className("location__distance")).getText();
    System.out.println(distance); // printing distance of first search item

    firstRepeaterElem.findElement(By.cssSelector(
        "a[ui-sref='mf.location({ locationId: location.id, slug: location.slugTitleGeo, predata: location })']"))
        .click();

    String titleAfterClick = driver.findElement(By.className("meeting-detail"))
        .findElement(By.xpath("//span[@ng-if='!linkName']")).getText();

    assertEquals(title, titleAfterClick);  // validating location before and after navigation to location link
  }


  @Test
  public void Test2() throws Exception {

    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
    String today = dateFormat.format(now);

    if (today.equalsIgnoreCase("Monday")) {
      System.out.println(driver.findElement(By.xpath(
          "//div[@class=\"meeting-hours meeting-detail-bottom-section meeting-hours--count-7\"]//ul/li[2]//div[@class=\"hours-list-item-hours\"]"))
          .getText());
    } else if (today.equalsIgnoreCase("Tuesday")) {
      System.out.println(driver.findElement(By.xpath(
          "//div[@class=\"meeting-hours meeting-detail-bottom-section meeting-hours--count-7\"]//ul/li[3]//div[@class=\"hours-list-item-hours\"]"))
          .getText());
    } else if (today.equalsIgnoreCase("Wednesday")) {
      System.out.println(driver.findElement(By.xpath(
          "//div[@class=\"meeting-hours meeting-detail-bottom-section meeting-hours--count-7\"]//ul/li[4]//div[@class=\"hours-list-item-hours\"]"))
          .getText());
    } else if (today.equalsIgnoreCase("Thursday")) {
      System.out.println(driver.findElement(By.xpath(
          "//div[@class=\"meeting-hours meeting-detail-bottom-section meeting-hours--count-7\"]//ul/li[5]//div[@class=\"hours-list-item-hours\"]"))
          .getText());
    } else if (today.equalsIgnoreCase("Friday")) {
      System.out.println(driver.findElement(By.xpath(
          "//div[@class=\"meeting-hours meeting-detail-bottom-section meeting-hours--count-7\"]//ul/li[6]//div[@class=\"hours-list-item-hours\"]"))
          .getText());
    } else if (today.equalsIgnoreCase("Saturday")) {
      System.out.println(driver.findElement(By.xpath(
          "//div[@class=\"meeting-hours meeting-detail-bottom-section meeting-hours--count-7\"]//ul/li[7]//div[@class=\"hours-list-item-hours\"]"))
          .getText());
    } else if (today.equalsIgnoreCase("Sunday")) {
      System.out.println(driver.findElement(By.xpath(
          "//div[@class=\"meeting-hours meeting-detail-bottom-section meeting-hours--count-7\"]//ul/li[1]//div[@class=\"hours-list-item-hours\"]"))
          .getText());
    }
  }

  @Test

  public void Test3() throws Exception {

    List<WebElement> elements = driver.findElements(By.xpath(
        "//div[@class='schedule-detailed-day']//div[@class='schedule-detailed-day-meetings-item']"));

    ListIterator<WebElement> theListOfElements = elements.listIterator();

    Map<String, Integer> map = new HashMap<>();

    while (theListOfElements.hasNext()) {
      WebElement elem = theListOfElements.next();
      String name = elem.findElement(By.className("schedule-detailed-day-meetings-item-leader"))
          .getText();

      if (map.get(name) != null) {
        map.put(name, map.get(name) + 1);
      } else {
        map.put(name, 1);
      }
    }

    map.forEach((key, value) -> System.out.println(key + ":" + value));
  }
}




