@Step("Двойной клик по веб-элементу ${elementName} на странице ${pageName}")
public ${pageName} click${elementName}() {
    ${elementName}.click().click();

    return this;
}