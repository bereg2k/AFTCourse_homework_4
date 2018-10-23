import sweets.Chocolate;
import sweets.Jellybean;
import sweets.Lollipop;
import sweets.Sweets;

import java.util.Scanner;

/**
 * Задание "Упаковка подарка".
 * Формируется новогодний подарок. Он может включать в себя разные сладости (Candy, sweets.Jellybean, etc.)
 * У каждой сладости есть название, вес, цена и свой уникальный параметр.
 * Необходимо собрать подарок из сладостей.
 * Найти общий вес подарка, общую стоимость подарка и вывести на консоль информацию о всех сладостях в подарке.
 * <p>
 * Пользователь может добавлять по одному виду каждой сладости за раз (сейчас есть 3 вида).
 *
 * @author Oleg Berezhnoy
 */
public class Gift {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //создаем объекты классов для доступных видов сладостей: шоколад, мармелад, леденцы
        Chocolate chocolate1 = new Chocolate("Alpen Gold Milky Ways", 100, 45.99, "молочный");
        Chocolate chocolate2 = new Chocolate("Alpen Gold Cappuccino", 100, 68.99, "молочный");
        Chocolate chocolate3 = new Chocolate("Lindt 99%", 100, 155.50, "тёмный");
        Chocolate chocolate4 = new Chocolate("Snickers KingSize", 89.45, 65.25, "молочный");
        Chocolate chocolate5 = new Chocolate("Milka Arctic", 50.75, 75.10, "белый");

        Jellybean jellySB = new Jellybean("Haribo Bears", 90.54, 45.30, "клубника");
        Jellybean jellyBanana = new Jellybean("Skittles popups", 45, 30.90, "банан");

        Lollipop lolliChup = new Lollipop("Chupa-Chups Megastar", 40.99, 38.50, "XL");
        Lollipop lolliBubble = new Lollipop("Bubble Dinger Vanilla", 10.45, 20.50, "S");
        Lollipop lolliTop = new Lollipop("TOP of the POP", 15.99, 25.99, "M");

        //упаковываем все наименования каждого вида в отдельный массив
        Sweets chocolatePackage[] = new Sweets[]{chocolate1, chocolate2, chocolate3, chocolate4, chocolate5};
        Sweets jellybeanPackage[] = new Sweets[]{jellySB, jellyBanana};
        Sweets lollipopPackage[] = new Sweets[]{lolliChup, lolliBubble, lolliTop};

        System.out.println("\nВас приветствует \"Упаковка подарка!\"\n");

        GiftBox giftBox = new GiftBox();

        System.out.println(giftBox.toString());

        System.out.println("Добавим следующие сладости в коробку:");
        System.out.println(chocolate1.toString());
        giftBox.addSweetsToTheBox(chocolate1);

        System.out.println(chocolate5.toString());
        giftBox.addSweetsToTheBox(chocolate5);

        System.out.println(lolliBubble.toString());
        giftBox.addSweetsToTheBox(lolliBubble);

        System.out.println("И вот что получится в результате добавления сладостей в коробку:");
        System.out.println(giftBox.toString());

        System.out.println("Теперь заберем оттуда следующую сладость: ");
        System.out.println(chocolate1.toString());
        giftBox.removeSweetsFromTheBox(chocolate1);

        System.out.println("И вот что получится в результате вынимания сладостей из коробки:");
        System.out.println(giftBox.toString());

/*
        System.out.println("Теперь давайте конкретнее. Выберем чем конкретно наполним подарочек.");
        boolean badInput = false; //флаг для контроля некорректного ввода
        boolean wrapUp = false;
        while (!badInput & !wrapUp) {
            System.out.print("Введите '1' для добавления шоколада," +
                    " '2' - для мармелада, '3' - для леденцов или '4' для завершения: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Выберите нужную шоколадку для подарка из списка (введите её номер): ");
                    giftBox.addSweetsToTheBox(selectSweet(chocolatePackage));
                    break;
                case 2:
                    System.out.println("Выберите нужный мармелад для подарка из списка (введите его номер): ");
                    giftBox.addSweetsToTheBox(selectSweet(jellybeanPackage));
                    break;
                case 3:
                    System.out.println("Выберите нужный леденец для подарка из списка (введите его номер): ");
                    giftBox.addSweetsToTheBox(selectSweet(lollipopPackage));
                    break;
                case 4:
                    System.out.println("Пакуем подарок, спасибо!");
                    wrapUp = true;
                    break;
                default:
                    System.out.println("Введен неверный номер! Попробуйте ешё раз!"); //обработка некорректного ввода
                    badInput = true;
                    break;
            }
        }

        //распечатываем содержимое получившегося подарка
        System.out.println("Отличный выбор! Вот из чего состоит ваш подарок:");
        System.out.println(giftBox.toString());

        //вызываем методы подсчёта общего веса и стоимости подарка
        System.out.printf("\nОбщий вес подарка = %.2f г.", giftBox.getBoxWeight());
        System.out.printf("\nОбщая стоимость подарка = %.2f руб.", giftBox.getBoxPrice());
*/
        /*
        System.out.print("Введите нужное количество сладостей в подарке : ");
        int giftSize = scanner.nextInt();
        sweets.Sweets gift[] = new sweets.Sweets[giftSize];   //создаём массив для подарка нужного размера

        //наполняем подарок разными сладостями. Пользователь последовательно выбирает нужный вид и наименование.
        System.out.println("Теперь давайте конкретнее. Выберем чем конкретно наполним подарочек.");
        int choice;
        for (int i = 0; i < giftSize; i++) {
            System.out.print("Введите '1' для добавления шоколада, '2' - для мармелада или '3' - для леденцов: ");
            choice = scanner.nextInt();
            boolean badInput = false; //флаг для контроля некорректного ввода
            switch (choice) {
                case 1:
                    System.out.println("Выберите нужную шоколадку для подарка из списка (введите её номер): ");
                    gift[i] = selectSweet(chocolatePackage);
                    break;
                case 2:
                    System.out.println("Выберите нужный мармелад для подарка из списка (введите его номер): ");
                    gift[i] = selectSweet(jellybeanPackage);
                    break;
                case 3:
                    System.out.println("Выберите нужный леденец для подарка из списка (введите его номер): ");
                    gift[i] = selectSweet(lollipopPackage);
                    break;
                default:
                    System.out.println("Введен неверный номер! Попробуйте ешё раз!"); //обработка некорректного ввода
                    badInput = true; //теперь не будем выводить количество оставшегося места в подарке
                    i--; //возвращаем счётчик на шаг назад и начинаем снова
                    break;
            }

            if (i + 1 < gift.length & !badInput) { //выводим количество оставшегося места
                System.out.println("Ещё осталось добавить сладостей: " + (giftSize - i - 1) + ".\n");
            }
        }

        //распечатываем содержимое получившегося подарка
        System.out.println("Отличный выбор! Вот из чего состоит ваш подарок:");
        printSweetsArray(gift);

        //вызываем методы подсчёта общего веса и стоимости подарка
        System.out.printf("\nОбщий вес подарка = %.2f г.", getGiftTotalWeight(gift));
        System.out.printf("\nОбщая стоимость подарка = %.2f руб.", getGiftTotalPrice(gift));
*/

    }


    /**
     * Метод печати элементов массива из класса сладостей sweets.Sweets.
     * При распечатке добавляем порядковые номера для удобства (начинаем с 1, а не с 0!).
     *
     * @param sweetsArray массив сладостей в подарке
     */
    private static void printSweetsArray(Sweets sweetsArray[]) {
        for (int i = 0; i < sweetsArray.length; i++) {
            System.out.println((i + 1) + ". " + sweetsArray[i].toString());
        }
    }

    /**
     * Метод позволяет выбрать определенную сладость среди выбранного вида (шоколада, мармелада или леденцов)
     *
     * @param sweetsArray массив сладостей в подарке
     * @return выбранная сладость (в виде элемента массива sweets.Sweets)
     */
    private static Sweets selectSweet(Sweets sweetsArray[]) {
        printSweetsArray(sweetsArray);
        Scanner scanner = new Scanner(System.in);
        int sweetsChoice = scanner.nextInt();
        while (sweetsChoice > sweetsArray.length || sweetsChoice <= 0) { //обработка некорректного ввода
            System.out.println("Некорректный номер сладости! Давайте ещё разок!");
            sweetsChoice = scanner.nextInt();
        }
        return sweetsArray[sweetsChoice - 1]; //нумерация на экране начинается с 1 (не как в нормальной логике массивов)
    }
}
