package utils.testcase;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestSuite {
    private String name;
    private List<TestCase> testCases;
}
