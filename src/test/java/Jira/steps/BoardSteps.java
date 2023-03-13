package Jira.steps;

import Jira.hooks.AllureHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Flaky;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import static Jira.pageElements.BoardPage.*;
import static Jira.pageElements.PopupCreate.btnService;
import static Jira.steps.NavigationSteps.openMyBug;
import static com.codeborne.selenide.Selenide.sleep;

public class BoardSteps {
    public static void changeViewList() {
        btnChangeView.click();
        btnListView("Список").click();
    }

    @Flaky
    public static void checkNumTaskAll() {
        String numTasks = valueTaskAll.getText();
        try {
            Assertions.assertEquals(numTasks, valueTaskAll.getText(), "Неверное количеством задач");
        } catch (AssertionFailedError e) {
            AllureHelper.createScreenshot("Скриншот с количеством задач");
            throw new AssertionFailedError(e.getMessage());
        }
        Allure.addAttachment("Всего задач заведено в Jira", valueTaskAll.getText());
    }

    @Step("Переход к детальному просмотру списка")
    public static void changeView() {
        btnChangeView.click();
        btnListView("Детальный просмотр").click();
    }

    @Step("Поиск теста {nameTest}")
    public static void searchTest(String nameTest) {
        searchInput.sendKeys(nameTest);
        btnSearch.click();
        sleep(2000);
    }

    @Flaky
    @Step("Проверка теста: имя - {nameTest}, принадлежность к версии - {versionTest} ")
    public static void checkTest(String nameTest, String versionTest) {

        Allure.addAttachment("Имя теста", header.getText());
        Allure.addAttachment("Принадлежность к версии", versionText.getText());
        try {
            Assertions.assertEquals(header.getText(), nameTest, "Отличается название задачи от проверяемой");
            Assertions.assertEquals(versionText.getText(), versionTest, "Отличается принадлежность к версии");
        } catch (AssertionFailedError e) {
            AllureHelper.createScreenshot("Скриншот параметров задачи");
            throw new AssertionFailedError(e.getMessage());
        }
    }

    @Step("Перевод статуса задачи в Исполнено")
    public static void selectExecuted() {
        btnStatusBar("Бизнес-процесс").click();
        sleep(1000);
        btnStatusBug("Исполнено").click();
        btnService("Исполнено").click();

    }

    @Step("Перевод статуса задачи в Подтверждено")
    public static void selectConfirm() {
        btnStatusBar("Бизнес-процесс").click();
        sleep(1000);
        btnStatusBug("Подтверждено").click();
        btnService("Подтверждено").click();
        btnStatusBug("Выполнено").click();
    }

    @Flaky
    @Step("Проверка созданной задачи, по теме, описанию и окружении")
    public static void checkCreateBug(String theme, String description, String environment) {
        openMyBug();
        try {
            Assertions.assertEquals(header.getText(), theme, "Неверный заголовок задачи");
            Assertions.assertEquals(infoDescription.getText(), description, "Неверное описание задачи");
            Assertions.assertEquals(infoEnvironment.getText(), environment, "Неверное окружение задачи");
        } catch (AssertionFailedError e) {
            AllureHelper.createScreenshot("Скриншот деталей задачи");
            throw new AssertionFailedError(e.getMessage());
        }
    }

    @Flaky
    @Step("Проверка статуса задачи на {statusBug}")
    public static void checkStatusBag(String statusBug) {
        sleep(2000);
        try {
            Assertions.assertEquals(statusText.getText(), statusBug, "Неверный статус задачи");
        } catch (AssertionFailedError e) {
            AllureHelper.createScreenshot("Скриншот со статусом");
            throw new AssertionFailedError(e.getMessage());
        }
    }


}
