    @Step("Двойной клик на кнопку ${elementName} на странице ${pageName}")
    public ${pageName} doubleClick${elementName}Button() {
        ${elementName}.click();
        ${elementName}.click();

        return this;
    }