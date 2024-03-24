    @Step("Клик по веб-элементу ${elementName} на странице ${pageName}")
    public ${pageName}Page click${elementNameWithUpperCaseFirstLetter}() {
        ${elementName}.click();

        return this;
    }