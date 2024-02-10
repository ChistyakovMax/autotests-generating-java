package ru.example.generator.page;

import org.yaml.snakeyaml.Yaml;
import ru.example.generator.TemplateGenerator;
import ru.example.generator.step.StepGenerator;
import ru.example.generator.webelement.WebElementGenerator;
import utils.pageobject.StringTransformer;
import utils.pageobject.yaml.Element;
import utils.pageobject.yaml.Page;
import utils.pageobject.yaml.Pages;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PageGenerator {

    WebElementGenerator webElementGenerator = new WebElementGenerator();
    StepGenerator stepGenerator = new StepGenerator();
    Set<String> setOfWebElements = new HashSet<>();
    Set<String> setOfSteps = new HashSet<>();
    String templateFilePath = "FreeMakerPageObjectTemplate.ftl";
    Map<String, Object> elementsForTemplate = new HashMap<>();

    private Pages getPagesFromYAML() {
        Yaml yaml = new Yaml();
        InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("pages/pages.yaml");

        return yaml.loadAs(iStream, Pages.class);
    }

    //генерирует PageObject класс
    //1. генерация веб-элементов
    //2. генерация шагов для работы с веб-элементами
    public void generatePageObjectClass() throws Exception {
        Pages pages = getPagesFromYAML();

        //для каждой страницы создаем PageObject класс
        for (Page page : pages.getPages()) {
            setOfWebElements.clear();
            setOfSteps.clear();
            elementsForTemplate.clear();
            String pageName = page.getPageName();
            //для каждого элемента создаем веб-элементы и шаги в PageObject классе
            for (Element element : page.getElements()) {
                //генерация веб-элемента и добавление его в сет веб-элементов
                setOfWebElements.add(webElementGenerator.generateWebElementByTemplate(element));
                //генерация шагов для работы с веб-элементами и добавление их в сет шагов
                setOfSteps.addAll(stepGenerator.generateStepsByTemplate(element, pageName));
            }

            String webElements = StringTransformer.transformToString(setOfWebElements);
            String steps = StringTransformer.transformToString(setOfSteps);

            elementsForTemplate.put("pageName", pageName);
            elementsForTemplate.put("webElements", webElements);
            elementsForTemplate.put("steps", steps);

            String generatedPage = TemplateGenerator.generateFromTemplate(elementsForTemplate, templateFilePath);
            System.out.println(generatedPage);
        }
    }
}
