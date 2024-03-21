    @Step("Проверка, что элемент ${elementName} отображается на странице ${pageName}")
    public void assert${elementNameWithUpperCaseFirstLetter}IsDisplayed() {
        assertTrue(${elementName}.isDisplayed());
    }