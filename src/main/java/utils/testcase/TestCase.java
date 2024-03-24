package utils.testcase;

import lombok.Getter;
import lombok.Setter;
import utils.testcase.step.TestCaseStep;

import java.util.List;

@Getter
@Setter
public class TestCase {
    private String name;
    private List<TestCaseStep> testSteps;
}
