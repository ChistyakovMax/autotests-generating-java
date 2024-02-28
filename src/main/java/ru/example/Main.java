package ru.example;

import ru.example.generator.page.PageGenerator;

public class Main {
    public static void main(String[] args) {
        PageGenerator pg = new PageGenerator();
        try {
            pg.generatePageObjectClass();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}