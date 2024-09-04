import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;

//  в Edit configurations возле зеленой стрелки сверху слева в Идее выбрать Application, выбрать Main класс и прописать "values.json tests.json report.json" без кавычек,
//  так эти файлы будут передаваться параметрами, файлы должны лежать в корневой папке (те в TestPerformance)
//  нужно прописать зависимости fasterxml.jackson.core.databind  googlecode.json.simple
// Пример как:
// Откройте ваш проект в IntelliJ IDEA.
//  Перейдите в меню File, далее Project Structure
//  В окне Project Structure выберите вкладку Libraries
//  Нажмите кнопку добавить для добавления новой библиотеки
//  Выберите тип библиотеки From Maven и введите зависимости выше
public class Main {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //читаем аргуметы
            JsonNode values = mapper.readTree(new File(args[0])).get("values");
            JsonNode tests = mapper.readTree(new File(args[1])).get("tests");
            fillReport(tests, values);
            mapper.writeValue(new File(args[2]), tests);
            System.out.println("смотри report.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //заполняем отчет
    private static void fillReport(JsonNode tests, JsonNode values) {
        for (JsonNode test : tests) {
            if (test.has("values")) {
                fillReport(test.get("values"), values);
            } else {
                for (JsonNode value : values) {
                    if (test.get("id").asInt() == value.get("id").asInt()) {
                        ((ObjectNode) test).put("value", value.get("value").asText());
                    }
                }
            }
        }
    }
}