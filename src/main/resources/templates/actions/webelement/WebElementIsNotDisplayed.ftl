    @Step("Проверка, что элемент ${elementName} НЕ отображается на странице ${pageName}")
    public void assert${elementNameWithUpperCaseFirstLetter}IsNotDisplayed() {
        assertFalse(${elementName}.isDisplayed());
    }