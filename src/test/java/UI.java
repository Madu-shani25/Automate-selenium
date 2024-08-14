import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class UI {

    @Test
    public void Process(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

    //***Scenario 1: Users are able to search for an item using the search bar//

        // Navigate to Magento website homepage
        driver.get("https://magento.softwaretestingboard.com/");

        // Maximize the browser window
        driver.manage().window().maximize();

        // Perform a product search
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("bag");

        WebElement searchButton = driver.findElement(By.xpath("//button[@title='Search']"));
        searchButton.click();

        WebElement searchResults = driver.findElement(By.cssSelector(".products-grid .item"));
        if (searchResults.isDisplayed()) {
            System.out.println("Results are displayed.");
        } else {
            System.out.println("Results are not displayed.");
        }

    //**Scenario 2: Users are able to filter search results under Women’s “Tops” section by CATEGORY and COLOR**//

        // Navigate to Magento website homepage
        driver.get("https://magento.softwaretestingboard.com/");

        // Hover over the "Women" category
        WebElement womenCategory = driver.findElement(By.linkText("Women"));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenCategory).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));

        // After hovering, click on "Tops" under the "Women" dropdown
        // WebElement tops = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='ui-id-9' and text()='Tops']")));
        // actions.moveToElement(tops).click();

    //**Scenario 3: Users are able to view the details of any clothing item from the Gear section and add them to the cart**//

        // Navigate to Magento website homepage
        driver.get("https://magento.softwaretestingboard.com/");

        // Hover over the "Gear" menu to display the dropdown
        WebElement gearCategory = driver.findElement(By.linkText("Gear"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(gearCategory).perform();

        // Click on "Watches" under the "Gear" dropdown
        WebElement watches = driver.findElement(By.linkText("Watches"));
        watches.click();

        // Step 4: Click on the first item (e.g., the first bag)
        WebElement firstItem = driver.findElement(By.cssSelector(".products-grid .product-item:first-child"));
        firstItem.click();


        // Click the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Check that the item was added to the cart
        WebElement cartIcon = driver.findElement(By.cssSelector("a.showcart"));
        cartIcon.click();

        // Check if the cart contains the item
        WebElement cartItem = driver.findElement(By.cssSelector(".product-item-name"));
        if (cartItem.isDisplayed()) {
            System.out.println("Item successfully added to the Cart: " + cartItem.getText());
        } else {
            System.out.println("Failed to add the item to the Cart.");
        }

        driver.quit();
    }

}

