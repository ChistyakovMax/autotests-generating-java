    @Step("Двойной клик по веб-элементу ${elementName} на странице ${pageName}")
    public ${pageName}Page click${elementNameWithUpperCaseFirstLetter}() {
        ${elementName}.click().click();

        return this;
    }