import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//в Edit configurations возле зеленой стрелки сверху слева в Идее выбрать Application, выбрать Main класс и прописать "fileOfNumbers.txt" без кавычек,
//так эти файлы будут передаваться параметрами, файлы должны лежать в корневой папке (те в TestPerformance)

public class Main {

    public static void main(String[] args) {

        // пытаемся читать файл
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            ArrayList<Integer> nums = new ArrayList<>();
            String line;

            //если в нем строка есть вставляем ее в лист
            while ((line = reader.readLine()) != null) {
                nums.add(Integer.parseInt(line));
            }

            //получаем минимум шагов
            int minMoves = getMinMoves(nums);
            System.out.println(minMoves);

        } catch (IOException e) {
            System.err.println("ошибка при чтении файла : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("формат чтения файла неверен  ");
        }
    }

    public static int getMinMoves(ArrayList<Integer> nums) {
        //сортировка по возрастанию
        Collections.sort(nums);
        //находим среднее число
        int median = nums.get(nums.size() / 2);
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}