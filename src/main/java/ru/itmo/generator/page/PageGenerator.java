package ru.itmo.generator.page;

import org.yaml.snakeyaml.Yaml;
import ru.itmo.filewriter.FileCreator;
import ru.itmo.generator.TemplateGenerator;
import ru.itmo.generator.page.step.StepGenerator;
import ru.itmo.generator.page.webelement.WebElementGenerator;
import utils.Prettier;
import utils.StringTransformer;
import utils.pageobject.yaml.Element;
import utils.pageobject.yaml.Page;
import utils.pageobject.yaml.Pages;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static utils.Constants.BASE_PAGE_NAME;

public class PageGenerator {

    private WebElementGenerator webElementGenerator = new WebElementGenerator();
    private StepGenerator stepGenerator = new StepGenerator();
    private Set<String> webElements = new HashSet<>();
    private Set<String> steps = new HashSet<>();
    private final String pageObjectTemplateFilePath = "page/PageObjectTemplate.ftl";
    private final String basePageTemplateFilePath = "page/BasePageTemplate.ftl";
    private Map<String, Object> elementsForTemplate = new HashMap<>();

    public Pages getPagesFromYAML() {
        Yaml yaml = new Yaml();
        InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("pages/pages.yaml");
        Pages pages = yaml.loadAs(iStream, Pages.class);
        for(Page page: pages.getPages()) {
            //для каждой страницы изменяем имя на соответствующее
            page.setPageName(Prettier.getNameWithUpperCaseFirstLetter(page.getPageName()));
            for(Element element: page.getElements()) {
                //для каждого элемента изменяем имя на соответствующее
                element.setElementName(Prettier.getElementNameForGenerate(element));
            }
        }

        return pages;
    }

    //генерирует PageObject класс
    //1. генерация веб-элементов
    //2. генерация шагов для работы с веб-элементами
    public void generatePages() throws Exception {
        Pages pages = getPagesFromYAML();
        //генерация BasePage
        generateBasePage(pages);
        //для каждой страницы создаем PageObject класс
        for (Page page : pages.getPages()) {
            if (page.getPageName().equals("Base")) continue;

            webElements.clear();
            steps.clear();
            elementsForTemplate.clear();
            String pageName = page.getPageName();
            String additionalUrl = page.getAdditionalUrl();

            //для каждого элемента создаем веб-элементы и шаги в PageObject классе
            for (Element element : page.getElements()) {
                //генерация веб-элемента и добавление его в сет веб-элементов
                webElements.add(webElementGenerator.generateWebElementByTemplate(element));
                //генерация шагов для работы с веб-элементами и добавление их в сет шагов
                steps.addAll(stepGenerator.generateStepsByTemplate(element, pageName));
            }

            String webElements = StringTransformer.transformToStringWithTwoEnters(this.webElements);
            String steps = StringTransformer.transformToStringWithTwoEnters(this.steps);

            elementsForTemplate.put("pageName", pageName);
            elementsForTemplate.put("webElements", webElements);
            elementsForTemplate.put("steps", steps);
            elementsForTemplate.put("additionalUrl", additionalUrl);

            String pageObject = TemplateGenerator.generateFromTemplate(elementsForTemplate, pageObjectTemplateFilePath);
            System.out.println(pageObject);
            FileCreator.createPageObjectClass(pageObject, pageName);
        }
    }

    private void generateBasePage(Pages pages) throws Exception {
        Page basePage = null;
        webElements.clear();
        steps.clear();
        elementsForTemplate.clear();
        elementsForTemplate.put("baseUrl", pages.getBaseUrl());
        
        for(Page page: pages.getPages()) {
            if(page.getPageName().equals("Base")) {
                basePage = page;
                break;
            }
        }

        if(basePage == null) {
            elementsForTemplate.put("webElements", "");
            elementsForTemplate.put("steps", "");
        } else {
            //для каждого элемента создаем веб-элементы и шаги в PageObject классе
            for (Element element : basePage.getElements()) {
                //генерация веб-элемента и добавление его в сет веб-элементов
                webElements.add(webElementGenerator.generateWebElementByTemplate(element));
                //генерация шагов для работы с веб-элементами и добавление их в сет шагов
                steps.addAll(stepGenerator.generateStepsByTemplate(element,BASE_PAGE_NAME));
            }

            String webElements = StringTransformer.transformToStringWithTwoEnters(this.webElements);
            String steps = StringTransformer.transformToStringWithTwoEnters(this.steps);

            elementsForTemplate.put("webElements", webElements);
            elementsForTemplate.put("steps", steps);
        }
        String basePageObject = TemplateGenerator.generateFromTemplate(elementsForTemplate, basePageTemplateFilePath);
        System.out.println(basePageObject);
        FileCreator.createPageObjectClass(basePageObject, BASE_PAGE_NAME);
    }

}
