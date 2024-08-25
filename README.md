<h1>Данный генератор позволяет генерировать e2e автотесты с использованием библиотек Selenium WebDriver и jUnit4 на основе текстового представления сценариев и YAML файла для генерации Page Object класса.</h1>

1.  <b>Заполните YAML файл</b> (src/main/resources/pages/pages.yaml) данными о страницах вашего веб-приложения для генерации PageObject класса.
![image](https://github.com/user-attachments/assets/4a3ad97d-43a3-4aef-886c-672b104d1ad1)

2.  <b>Добавьте в директорию src/main/resources/manual_tests/ один или несколько файлов с описанием тестового сценария.</b> Каждый файл является тест-сьютом, который может иметь своё наименование и тестовые сценарии. В тестовых же сценариях содержатся название ТК и шаги, включая комментарии.
![image](https://github.com/user-attachments/assets/67871c18-6ea4-4648-8b50-9aeeeb7a8c9b)

3. <b>Запустите проект для генерации PageObject и классов тестов.</b> Сгенерированные PageObject классы будут находиться в директории src/main/java/ru/itmo/pages/. Сгенерированные классы автотестов - в директории src/test/java/ru/itmo/.
![image](https://github.com/user-attachments/assets/aa1e4b85-62d0-4635-8dd0-c0938d891291)

4.  <b>После запуска выполняется автоматическая генерация Allure-отчёта.</b>
![image](https://github.com/user-attachments/assets/4c79ed87-9fac-4c75-ac71-fc39bf610566)

