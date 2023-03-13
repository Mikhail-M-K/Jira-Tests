package Jira.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static Jira.Utils.ConfProperties.getProperty;
import static Jira.pageElements.LoginPage.btnEnter;
import static Jira.steps.AuthenticationSteps.authentication;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class WebHooks {

    @BeforeAll
    public static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @BeforeEach
    @Step("Аутентификация")
    public void authenticationProcess() {
        Configuration.startMaximized = true;
        open(getProperty("login-page"));
        authentication(getProperty("login"), getProperty("password"));
        btnEnter.click();
    }

    @AfterEach
    public void afterTest() {
        closeWebDriver();
    }
}
