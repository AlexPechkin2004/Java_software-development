/*
        Визначити C2 як остачу від ділення номера залікової книжки студента на 2, C3 як
        остачу від ділення номера залікової книжки студента на 3, C5 як остачу від
        ділення номера залікової книжки студента на 5, C7 як остачу від ділення номера
        залікової книжки студента на 7.

        Номер залікової книги - 1124
        C2 = 0 (О1 +)
        C3 = 2 (константа С)
        С5 = 4 (О2 -)
        С7 = 4 (тип індексів i та j - char)
*/

import java.util.Scanner;
import java.text.DecimalFormat;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Увага! В арифметичних операціях будуть оброблятись коди відповідних символів в ASCII.");
        System.out.println("Якщо введено більше одного символа в рядок, обробляється лише перший символ.\n");

        System.out.print("Введіть символ m: ");
        char m = scanner.next().charAt(0);
        System.out.print("Введіть символ n: ");
        char n = scanner.next().charAt(0);

        System.out.print("Введіть символ a: ");
        char a = scanner.next().charAt(0);
        System.out.print("Введіть символ b: ");
        char b = scanner.next().charAt(0);

        double sum = 0;
        for (char i = a; i <= n; i++) {
            for (char j = b; j <= m; j++) {
                sum+=(i - j)/(double)(i + 2);
            }
        }
        DecimalFormat df = new DecimalFormat("#.#############");
        System.out.println("Результат: " + df.format(sum));
    }
}