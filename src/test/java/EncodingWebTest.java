import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class EncodingWebTest {

    @Test
    void encodingWebTest() {
        Configuration.browserSize = "1920x1080";
        open("https://api.encoding.com/");
        $(".Header-search-placeholder3Vx4OmMPcjAi").click();
        $(".SearchBox-InputUQZAW9QXMe-c").sendKeys("getStatus");
        $(".SearchResults-list").$(byText("GetStatus (extended)")).click();
        webdriver().shouldHave(url("https://api.encoding.com/reference/responses-getstatus-extended"));
        $(".CodeTabs-toolbar", 1).$(byText("JSON")).click();
        $$(".cm-s-neo span").find(text("processor")).sibling(0).shouldHave(text("AMAZON"), text("RACKSPACE"));
        $$(".cm-s-neo span").find(text("status")).sibling(0).shouldHave(text("Status"));
        closeWebDriver();
    }


}
