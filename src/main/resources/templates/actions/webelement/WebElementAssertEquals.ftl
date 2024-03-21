    @Step("Проверка, что текст элемента ${elementName} соответствует параметру")
    public void assert${elementNameWithUpperCaseFirstLetter}Equals(String expected) {
        assertEquals(expected, getText${elementNameWithUpperCaseFirstLetter}());
    }