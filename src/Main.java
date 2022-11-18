import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void answering(String name, MyTable table) {
        Scanner in = new Scanner(System.in);
        System.out.println("Отвечает " + name + ".");
        System.out.println("Присутствует на паре: ");
        int isHere = in.nextInt();
        int ans = 0;
        if (isHere == 1) {
            System.out.println("Оценка за ответ: ");
            ans = in.nextInt();
        }
        table.insert(name, isHere, ans);
    }

    public static void main(String[] args) {
        MyTable table = new MyTable("students.txt");
        Scanner scanner = new Scanner(System.in);
        int flag = 1;
        while (flag == 1) {
            String input = scanner.nextLine();
            if (input.length() > 3 && input.substring(0, 2).equals("/c")) {
                String name = input.substring(3);
                answering(name, table);
            } else {
                input = input.substring(0, 2);
                switch (input) {
                    case ("/h"):
                        System.out.println("""
                                /r - выбрать случайного студента
                                /l - получить список студентов
                                /c <Имя> <Фамилия> - выбрать студента с данным именем и фамилией
                                /q - выход
                                """);
                        break;
                    case ("/r"):
                        String name = table.chooseStudent();
                        answering(name, table);
                        break;
                    case ("/l"):
                        ArrayList<Pair<String, Integer>> names = table.getStudents();
                        for (Pair<String, Integer> x : names) {
                            System.out.println(x.first + ": " + x.second);
                        }
                        break;
                    case ("/q"):
                        flag = 0;
                    default:
                        break;
                }
            }
        }
        table.write("students.txt");
    }
}
