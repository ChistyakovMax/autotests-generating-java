package utils.pageobject.yaml;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page {
    private String pageName;
    private String additionalUrl;
    private List<Element> elements;

}
