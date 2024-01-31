package utils.pageobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Element {
    private String elementName;
    public enum ElementType {
        BUTTON,
        INPUT,
        TEXTFIELD
    };
    private String locator;
}
