    @Step("Двойной клик на кнопку ${elementName} на странице ${pageName}")
    public ${pageName}Page doubleClick${elementNameWithUpperCaseFirstLetter}() {
        ${elementName}.click();
        ${elementName}.click();

        return this;
    }