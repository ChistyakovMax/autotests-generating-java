package utils.testcase.step;

import lombok.Getter;
import utils.testcase.types.TestStepType;

@Getter
public class TestCaseStepComment extends TestCaseStep{
    private String comment = "";

    public TestCaseStepComment(String comment) {
        testStepType = TestStepType.COMMENT;
        this.comment = comment;
    }
}
