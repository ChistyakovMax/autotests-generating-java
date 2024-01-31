package ru.example;

import ru.example.pages_generator.PagesGenerator;

public class Main {
    public static void main(String[] args) throws Exception {
        PagesGenerator pg = new PagesGenerator();
        pg.generatePageObject();
    }
}