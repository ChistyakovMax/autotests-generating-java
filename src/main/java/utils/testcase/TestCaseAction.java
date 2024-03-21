package utils.testcase;

import utils.testcase.types.ActionType;

public class TestCaseAction extends TestCaseStep {

    private ActionType actionType;
    private String fillingText = "";
    private String reachedUrl = "";

    public TestCaseAction(String actionFromFile) {
        //Click SearchScreen.searchBar
        //fill SearchScreen.searchBar with Test text
        //double_click SearchScreen.searchBar
        //clear SearchScreen.searchBar
        //Go_to https://something.com
        actionType = ActionType.valueOf(actionFromFile.split(" ")[0].toUpperCase());
        switch (actionType) {
            case FILL:
                fillingText = actionFromFile.split(" with ")[1];
            case GO_TO:
                reachedUrl = actionFromFile.split(" ")[1];
            case CLICK:
            case DOUBLE_CLICK:
            case CLEAR:
                pageName = getPageName(actionFromFile);
                elementName = getElementName(actionFromFile);
            break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + actionType);
        }
    }
}
