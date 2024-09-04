import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//в Edit configurations возле зеленой стрелки сверху слева в Идее выбрать Application, выбрать Main класс и прописать "circle.txt points.txt" без кавычек,
//так эти файлы будут передаваться параметрами, файлы должны лежать в корневой папке (те в TestPerformance)
public class Main {
    public static void main(String[] args) {
        //считываем файлы
        try (BufferedReader circleReader = new BufferedReader(new FileReader(args[0]));
             BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]))) {

            //координаты окружности считываем в массив
            String[] circleData = circleReader.readLine().split(" ");
            int centerX = Integer.parseInt(circleData[0]);
            int centerY = Integer.parseInt(circleData[1]);
            int radius = Integer.parseInt(circleReader.readLine());

            //считываем массив координат окружности, елси она не пустой
            //то считываем координаты точек в массив
            String line;
            while ((line = pointsReader.readLine()) != null) {
                String[] pointData = line.split(" ");
                int pointX = Integer.parseInt(pointData[0]);
                int pointY = Integer.parseInt(pointData[1]);

                //по формуле высчитываем дистанцию
                int distance = (int) Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

                if (distance < radius) {
                    System.out.println("1"); // точка внутри окружности
                } else if (distance == radius) {
                    System.out.println("0"); // точка на окружности
                } else {
                    System.out.println("2"); // точка снаружи окружности
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файлов: " + e.getMessage());
            System.exit(1);
        }
    }
}