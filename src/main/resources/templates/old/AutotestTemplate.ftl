package ru.example.freemarker.tests;

import org.junit.Before;
import org.junit.Test;
import ru.example.screens.*;

public class TestCase${testNumber} extends BaseTest {

    ${testCaseScreens}

    @Before
    public void beforeMethod() {

    }

    @Test
    public void testCase${testNumber}() {
        ${testCaseSteps}
    }
}