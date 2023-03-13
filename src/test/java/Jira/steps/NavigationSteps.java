package Jira.steps;

import io.qameta.allure.Step;

import static Jira.pageElements.NavigationBar.btnNavigationBar;
import static Jira.pageElements.PopupCreate.*;


public class NavigationSteps {

    @Step("Переход в Поиск задач")
    public static void openAllTasks() {
        btnNavigationBar("Задачи").click();
        btnNavigationBar("Поиск задач").click();
    }

    @Step("Переход к сообщенным задачам")
    public static void openMyBug() {
        btnNavigationBar("Задачи").click();
        btnNavigationBar("Сообщенные мной").click();
    }

    @Step("Создание задачи с навигационной панели")
    public static void createBugNavigation(String theme, String description, String environment) {
        btnNavigationBar("Создать").click();
        inputTheme.sendKeys(theme);
        inputDescription.sendKeys(description);
        btnText.scrollTo().click();
        selectVersion.click();
        forScroll.scrollTo();
        inputEnvironment.sendKeys(environment);
        btnService("Создать").click();
    }
}
