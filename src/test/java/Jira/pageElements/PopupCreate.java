package Jira.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PopupCreate {
    public static SelenideElement inputTheme = $x("//label[contains(text(),'Тема ')]/following-sibling::input").as("Ввод темы");

    public static SelenideElement inputDescription = $x("//textarea[@id='description']").as("Ввод описания");

    public static SelenideElement btnText = $x("//button[contains(text(),'Текст')]").as("Кнопка Текст");

    public static SelenideElement selectVersion = $x("//option[contains(text(),'Version 2.0')]").as("Исправить в версиях");

    public static SelenideElement inputEnvironment = $x("//textarea[@id='environment']").as("Ввод окружения");

    public static SelenideElement forScroll = $x("//div[contains(text(),'Введите, чтобы найти')]").as("Ввод темы");

    public static SelenideElement btnService(String btnName) {
        return $x("//input[@value='" + btnName + "']").as("Кнопка " + btnName);
    }
}
