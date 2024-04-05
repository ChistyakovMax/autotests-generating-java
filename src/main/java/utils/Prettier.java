package utils;

import utils.pageobject.ElementType;
import utils.pageobject.yaml.Element;

public class Prettier {
    public static String getElementNameForGenerate(Element element) {
        ElementType elementType = ElementType.valueOf(element.getElementType().toUpperCase());
        String newElementName = element.getElementName().substring(0,1).toLowerCase()
                + element.getElementName().substring(1);

        switch (elementType) {
            case BUTTON:
                return  newElementName + "Button";

            case INPUT:
                return newElementName + "Input";

            default:
                return  newElementName;
        }
    }

    public static String getNameWithUpperCaseFirstLetter(Element element) {
        return element.getElementName().substring(0,1).toUpperCase()
                + element.getElementName().substring(1);
    }

    public static String getNameWithUpperCaseFirstLetter(String element) {
        return element.substring(0,1).toUpperCase()
                + element.substring(1);
    }

    public static String getElementNameWithLowerCaseFirstLetter(String element) {
        return element.substring(0,1).toLowerCase()
                + element.substring(1);
    }
}
