package ru.example.pages_generator;

import org.yaml.snakeyaml.Yaml;
import utils.pageobjects.Pages;

import java.io.InputStream;

public class PagesGenerator {

    public Pages getPagesFromYAML() {
        Yaml yaml = new Yaml();
        InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("pages/pages.yaml");
        Pages pages = yaml.loadAs(iStream, Pages.class);

        return pages;
    }
}
