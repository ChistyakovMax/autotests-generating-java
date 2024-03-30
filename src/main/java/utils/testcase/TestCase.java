package utils.testcase;

import lombok.Getter;
import lombok.Setter;
import utils.testcase.step.TestCaseStep;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TestCase {
    private String name;
    private Set<String> pages;
    private List<TestCaseStep> testSteps;
}
