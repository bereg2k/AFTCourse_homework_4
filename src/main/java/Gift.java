import sweets.Chocolate;
import sweets.Jellybean;
import sweets.Lollipop;
import sweets.Sweets;

import java.util.Iterator;
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

        GiftBox giftBox = new GiftBox(); //создаём новую подарочную коробку

        System.out.println("Теперь давайте конкретнее. Выберем чем конкретно наполним подарочек.");
        boolean badInput = false; //флаг для контроля некорректного ввода
        boolean wrapUp = false;
        while (!badInput & !wrapUp) {
            System.out.print("\nВведите '1' для добавления шоколада, '2' - для мармелада, '3' - для леденцов,\n" +
                    "'4' - посмотреть что в коробке,'5' - для УДАЛЕНИЯ сладостей из коробки,'6' для завершения упаковки: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Выберите нужную шоколадку для подарка из списка (введите её номер): ");
                    giftBox.addSweetsToTheBox(selectSweetsToAdd(chocolatePackage));
                    break;
                case 2:
                    System.out.println("Выберите нужный мармелад для подарка из списка (введите его номер): ");
                    giftBox.addSweetsToTheBox(selectSweetsToAdd(jellybeanPackage));
                    break;
                case 3:
                    System.out.println("Выберите нужный леденец для подарка из списка (введите его номер): ");
                    giftBox.addSweetsToTheBox(selectSweetsToAdd(lollipopPackage));
                    break;
                case 4:
                    System.out.println("Вот текущее содержимое коробки: ");
                    giftBox.showBoxContentWithPriceWeight();
                    break;
                case 5:
                    giftBox.removeSweetsFromTheBox(selectSweetsToRemove(giftBox)); //вызываем метод удаления выбранной сладости
                    break;
                case 6:
                    System.out.println("\nПакуем подарок, спасибо!");
                    wrapUp = true;
                    break;
                default:
                    System.out.println("Введен неверный номер! Попробуйте ешё раз!"); //обработка некорректного ввода
                    badInput = true;
                    break;
            }
        }

        System.out.println("\nВот итоговое содержимое нашей коробки: ");
        giftBox.showBoxContentWithPriceWeight();
    }

    /**
     * Метод позволяет выбрать определенную сладость среди выбранного вида (шоколада, мармелада или леденцов)
     *
     * @param sweetsArray массив сладостей в подарке
     * @return выбранная сладость (в виде элемента массива sweets.Sweets)
     */
    private static Sweets selectSweetsToAdd(Sweets sweetsArray[]) {

        for (int i = 0; i < sweetsArray.length; i++) { //распечатываем переданный массив сладостей для выбора
            System.out.println((i + 1) + ". " + sweetsArray[i].toString());
        }

        Scanner scanner = new Scanner(System.in);
        int sweetsChoice = scanner.nextInt();
        while (sweetsChoice > sweetsArray.length || sweetsChoice <= 0) { //обработка некорректного ввода
            System.out.println("Некорректный номер сладости! Давайте ещё разок!");
            sweetsChoice = scanner.nextInt();
        }
        return sweetsArray[sweetsChoice - 1]; //нумерация на экране начинается с 1 (не как в нормальной логике массивов)
    }

    /**
     * Метод позволяет выбрать определенную сладость из выбранной подарочной коробки для удаления.
     *
     * @param giftBox подарочная коробка со сладостями
     * @return выбранная сладость класса sweets.Sweets для удаления из коробки
     */
    private static Sweets selectSweetsToRemove(GiftBox giftBox) {
        Scanner scanner = new Scanner(System.in);

        if (giftBox.getSweetsInTheBox().isEmpty()) { //для удаления в коробке должны быть сладости
            System.out.println("Ваша коробка пуста, тут нечего вынимать!");
            return null;
        } else {
            System.out.println("Какую сладость вы хотите удалить?");
            giftBox.showBoxContent();
            int removeChoice = scanner.nextInt();

            if (removeChoice <= 0 || removeChoice > giftBox.getSweetsInTheBox().size()) { //контроль некорректного ввода
                System.out.println("Введен некорректный порядковый номер сладости для удаления!");
                return null;
            }

            //проходим по содержимому коробки с помощью итератора.
            Iterator iterator = giftBox.getSweetsInTheBox().iterator();
            Sweets sweetsToRemove = null;
            for (int i = 0; i < removeChoice; i++) {
                sweetsToRemove = (Sweets) iterator.next();
            }
            return sweetsToRemove;
        }
    }
}
