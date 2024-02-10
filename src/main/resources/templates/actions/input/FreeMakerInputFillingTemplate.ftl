@Step("Заполнить поле ${elementName} на странице ${pageName}")
public ${pageName} fill${elementName}Input(String parameter) {
    ${elementName}.sendKeys(parameter);

    return this;
}