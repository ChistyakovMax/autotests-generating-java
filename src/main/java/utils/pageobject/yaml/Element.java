package utils.pageobject.yaml;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Element {
    private String elementName;
    private String elementType;
    private String elementLocator;

    @Override
    public String toString() {
        return String.format("%s %s %s\n\n", elementName, elementType, elementLocator);
    }
}
