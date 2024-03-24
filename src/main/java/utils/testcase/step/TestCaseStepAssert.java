package utils.testcase.step;

import lombok.Getter;
import lombok.Setter;
import utils.testcase.types.TestStepAssertType;
import utils.testcase.types.TestStepType;

@Getter
@Setter
public class TestCaseStepAssert extends TestCaseStep {

    private TestStepAssertType testStepAssertType;
    private String expectedText = "";
    private String expectedUrl = "";

    public TestCaseStepAssert(String assertFromFile) {
        //Assert SearchScreen.cancel is_displayed
        //Assert SearchResultScreen.searchText equals t-shirt
        //Assert https://something.com is_current_url
        testStepType = TestStepType.ASSERT;
        testStepAssertType = TestStepAssertType.valueOf(assertFromFile.split(" ")[2].toUpperCase());
        switch (testStepAssertType) {
            case EQUALS:
                expectedText = assertFromFile.split(" equals ")[1];
            case IS_DISPLAYED:
            case IS_NOT_DISPLAYED:
                pageName = getPageName(assertFromFile);
                elementName = getElementName(assertFromFile);
                break;

            case IS_CURRENT_URL:
                expectedUrl = assertFromFile.split(" ")[1].toLowerCase();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + testStepAssertType);
        }
    }

}
