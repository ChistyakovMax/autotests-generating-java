    @Step("Клик на кнопку ${elementName} на странице ${pageName}")
    public ${pageName}Page click${elementNameWithUpperCaseFirstLetter}() {
        ${elementName}.click();

        return this;
    }