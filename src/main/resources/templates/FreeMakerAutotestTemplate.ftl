package ru.example.freemaker.tests;

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