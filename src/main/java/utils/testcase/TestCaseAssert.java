package utils.testcase;

import lombok.Getter;
import lombok.Setter;
import utils.testcase.types.AssertType;

@Getter
@Setter
public class TestCaseAssert extends TestCaseStep{

    private AssertType assertType;
    private String expectedText = "";
    private String expectedUrl = "";

    public TestCaseAssert(String assertFromFile) {
        //Assert SearchScreen.cancel is_displayed
        //Assert SearchResultScreen.searchText equals t-shirt
        //Assert current_url_is https://something.com
        assertType = AssertType.valueOf(assertFromFile.split(" ")[2].toUpperCase());
        switch (assertType) {
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
                throw new IllegalArgumentException("Unexpected value: " + assertType);
        }
    }

}
