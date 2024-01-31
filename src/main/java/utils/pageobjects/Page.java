package utils.pageobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page {
    private String pageName;
    private List<Element> elements;

}
