package utils;

import java.util.Set;

public class Constants {
    //пути файлов
    public static final String TESTS_PATH = "src/main/resources/manual_tests/";
    public static final String PAGES_PATH = "./src/main/java/ru/itmo/pages/";
    public static final String TEST_SUITES_PATH = "./src/test/java/ru/itmo/";
    //для проверки при генерации
    public static final String BASE_PAGE_NAME = "Base";
    public static final String ASSERTION = "ASSERT";
    public static final Set<String> ACTIONS = Set.of("CLICK", "DOUBLE_CLICK", "FILL", "CLEAR", "GO_TO_PAGE", "GO_TO_URL", "WAIT_FOR_ELEMENT");
    public static final String wrongTestStepTypeExceptionMessage = "Строка должна начинаться с " + ASSERTION + " либо " + ACTIONS + "!";
}
