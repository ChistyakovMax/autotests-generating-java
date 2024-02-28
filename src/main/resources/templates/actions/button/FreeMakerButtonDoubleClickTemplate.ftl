    @Step("Двойной клик на кнопку ${elementName} на странице ${pageName}")
    public ${pageName}Page doubleClick${elementName}Button() {
        ${elementName}.click();
        ${elementName}.click();

        return this;
    }