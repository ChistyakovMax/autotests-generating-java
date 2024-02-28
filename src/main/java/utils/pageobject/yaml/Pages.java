package utils.pageobject.yaml;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pages {
    private String baseUrl;
    private List<Page> pages;
}
