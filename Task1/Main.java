import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //вводим в консоль длину массива и шаг внутри него
        //по хорошему можно было написать проверку что пользователь вводит числа,
        //но я думаю что для задания нужно написать только код который нужен только для выполнения задания
        //т.е. без его растягивания его на проверки
        Scanner scanner = new Scanner(System.in);
        System.out.print("Напиши длину массива (n): ");
        int lengthMas = scanner.nextInt();

        System.out.print("Напиши шаг внутри массива (m): ");
        int stepMas = scanner.nextInt();

        System.out.println(getWay(lengthMas,stepMas));

        scanner.close();
    }


    public static String  getWay(int n, int m){
        int current=0;
        String way="";

        //заполняем массив
        int[] mas = new int[n];
        Arrays.setAll(mas, i -> ++i);

        //пока текущий индекс не равен 0:
        //добавляем к пути число с текущего положения индекса,
        //затем переступаем на новый индекс массива
        do {
            way += mas[current];
            current  = (current  + m - 1) % n;
        }
        while (current  != 0);
        return "длина: "+way;
    }
}