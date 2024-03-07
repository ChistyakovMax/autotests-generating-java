    @Step("Очистить поле ${elementName} на странице ${pageName}")
    public ${pageName}Page clear${elementName}Input() {
        ${elementName}.clear();

        return this;
    }