    @Step("Очистить поле ${elementName} на странице ${pageName}")
    public ${pageName}Page clear${elementNameWithUpperCaseFirstLetter}() {
        ${elementName}.clear();

        return this;
    }