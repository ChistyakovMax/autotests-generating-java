package ru.itmo.freemarker.tests;

import org.junit.Before;
import org.junit.Test;
import ru.itmo.screens.*;

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