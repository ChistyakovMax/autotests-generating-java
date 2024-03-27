package utils.testcase.step;

import lombok.Getter;
import lombok.Setter;
import utils.testcase.types.TestStepActionType;
import utils.testcase.types.TestStepType;

@Getter
@Setter
public class TestCaseStepAction extends TestCaseStep {

    private TestStepActionType testStepActionType;
    private String fillingText = "";
    private String reachedUrl = "";

    public TestCaseStepAction(String actionFromFile) {
        //Click SearchScreen.searchBar
        //fill SearchScreen.searchBar with Test text
        //double_click SearchScreen.searchBar
        //clear SearchScreen.searchBar
        //Go_to https://something.com
        testStepType = TestStepType.ACTION;
        testStepActionType = TestStepActionType.valueOf(actionFromFile.split(" ")[0].toUpperCase());
        switch (testStepActionType) {
            case GO_TO_URL:
                reachedUrl = actionFromFile.split(" ")[1];
                break;
            case FILL:
                fillingText = actionFromFile.split(" with ")[1];
            case WAIT_FOR_ELEMENT:
            case CLICK:
            case DOUBLE_CLICK:
            case CLEAR:
                pageName = getPageName(actionFromFile);
                elementName = getElementName(actionFromFile);
            break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + testStepActionType);
        }

    }
}
