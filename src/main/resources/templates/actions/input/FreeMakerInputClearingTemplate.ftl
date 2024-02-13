    @Step("Очистить поле ${elementName} на странице ${pageName}")
    public ${pageName} clear${elementName}Input() {
        ${elementName}.clear();

        return this;
    }