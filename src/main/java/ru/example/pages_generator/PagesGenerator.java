package ru.example.pages_generator;

import org.yaml.snakeyaml.Yaml;
import utils.pageobjects.Pages;

import java.io.InputStream;

public class PagesGenerator {

    private Pages getPagesFromYAML() {
        Yaml yaml = new Yaml();
        InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("pages/pages.yaml");

        return yaml.loadAs(iStream, Pages.class);
    }

    public void generatePageObjectClass() {
        Pages pages = getPagesFromYAML();
        System.out.println(pages.getPages().get(1).getElements().size());
    }
}
