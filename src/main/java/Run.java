import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.time.Duration;

public class Run {
    static WebDriver driver;
    public static void main(String[] args) {
        invokeBrowser();
        autoComplete("Israel","Israel National Trail");

    }
    public static void invokeBrowser(){
        try{
            driver= WebDriverManager.chromedriver().create();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.twoplugs.com/");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void autoComplete(String m,String n){
        driver.findElement(By.cssSelector("nav.nav>ul>li:nth-of-type(2)>a")).click();
        WebElement searchBox=driver.findElement(By.id("autocomplete"));
        searchBox.clear();
        searchBox.sendKeys(m);
        String text;
     do{
         searchBox.sendKeys(Keys.ARROW_DOWN);
         text=searchBox.getAttribute("value");
         if(text.contains(n)){
             searchBox.sendKeys(Keys.ENTER);
             break;
         }

     }while (!text.isEmpty());
    }
}
