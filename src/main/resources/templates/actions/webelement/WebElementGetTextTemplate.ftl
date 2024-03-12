    @Step("Взять текст у элемента ${elementName} на странице ${pageName}")
    public String getText${elementNameWithUpperCaseFirstLetter}() {
        return ${elementName}.getText();
    }