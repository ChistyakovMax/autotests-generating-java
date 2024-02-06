@Step("Двойной клик на кнопку ${elementName} на странице ${pageName}")
public ${pageName} doubleClick${elementName}Button() {
    ${elementName}.click().click();

    return this;
}