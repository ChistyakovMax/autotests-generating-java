@Step("Заполнить поле ${elementName}")
public ${pageName} fill${elementName}Input(String parameter) {
    ${elementName}.sendKeys(parameter);

    return this;
}