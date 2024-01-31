package ru.example.pages_generator;

import org.yaml.snakeyaml.Yaml;
import ru.example.models.Customer;
import java.io.InputStream;

public class PagesGenerator {

    public void generatePageObject() {
        Yaml yaml = new Yaml();
        InputStream iStream = this.getClass().getClassLoader().getResourceAsStream("pages/customer.yaml");
//      Map<String, Object> obj = yaml.load(iStream);

        Customer customer = yaml.loadAs(iStream, Customer.class);
        System.out.println(customer.getLastName());
    }
}
