import factory.LollipopFactory;
import factory.SweetsFactory;
import sweets.Chocolate;
import sweets.Jellybean;
import sweets.Lollipop;
import sweets.Sweets;

/**
 * Задание "Упаковка подарка".
 * 1) Реализовать: функциональный интерфейс фабрики сладостей - который имеет один абcтрактный метод по созданию вкусняшек.
 * Реализовать данный интерфейс 4 раза - 2 как отдельные классы, 2 в коде через лямбды (см в презентации Person::new)
 * то есть фабрика может генерировать только определенные классы сладостей, только печеньки или только шоколадки,
 * или строго по весу подбирать или по цене (тут на ваше усмотрение).
 * 2) Реализовать policy для коробочки - таким образом чтобы можно было создать коробку с определенным предикатом -
 * то есть добавление только определенных сладостей, которые соответствуют какому либо условию (опять-таки на свое усмотрение),
 * также реализовать 2 класса использующего данный интерфейс и позволить использовать лямбды.
 * 3) Реализовать внутри коробочки что-то типа CostCalculator  (будем считать что по умолчанию все цены в рублях,
 * а данная сущность будет его переводить в другие единицы - необходимо также создать интерфейс, и пару его реализаций -
 * рубли, евро и предоставить возможность объявить его в виде лямбды).
 * 4) Также добавить в коробочку несколько методов,  внутри использующих Stream API -
 * вывести название класса каждой сладости, посчитать количество определенного вида сладостей в коробке
 * 5) Для более симпатичной реализации классов самих сладостей, можете добавить ENUM’ы.
 * Там типа шоколада (белый, молочный, темный), размерности (S, M, XL) или еще что-то
 *
 * @author Oleg Berezhnoy
 */
public class Gift {
    public static void main(String[] args) {

        GiftBox giftBox = new GiftBox(); //создаём новую подарочную коробку

        //1. Создать фабрику по производству сладостей.
        //создание объекта класса с помощью лямбда-выражения
        System.out.println("1. Демонстрация работы фабрик по производству сладостей");

        System.out.println("Добавим немного шоколадных батончиков...");
        SweetsFactory marsBars = name -> new Chocolate(name, 80, 70, "молочный");
        giftBox.addSweetsToTheBox(marsBars.createSweets("MARS XL"));
        giftBox.addSweetsToTheBox(marsBars.createSweets("MARS Peanuts"));
        giftBox.showBoxContent();

        //создание объекта класса с помощью ссылки на метод (на конструктор)
        System.out.println("\nДобавим немного мишек Haribo...");
        SweetsFactory hariboProduction = Jellybean::new;
        giftBox.addSweetsToTheBox(hariboProduction.createSweets("Haribo Super Bears"));
        giftBox.addSweetsToTheBox(hariboProduction.createSweets("Haribo Bears From Space"));
        giftBox.showBoxContent();

        //создание объекта класса через класс конкретной фабрики
        System.out.println("\nДобавим немного продукции компании \"Chupa Chups\"...");
        LollipopFactory chupaChupsInc = new LollipopFactory();
        giftBox.addSweetsToTheBox(chupaChupsInc.createSweets("ChupaChups strawberry"));
        giftBox.addSweetsToTheBox(chupaChupsInc.createSweets("ChupaChups banana"));
        giftBox.showBoxContentWithPriceWeight();

        //2. Реализация policy для подарочной коробки.

        System.out.println("\n2. Демонстрация работы policy при добавлении сладостей в коробку.");
        System.out.println("\nДля следующего случая, сначала очистим полностью нашу подарочную коробку...");
        giftBox.getSweetsInTheBox().clear();
        giftBox.showBoxContent();

        System.out.println("\nЗадействуем policy для добавления сладостей в коробку...");
        giftBox.turnOnBoxPolicy("Chocolate");

        System.out.println("\nДавайте добавим \"неподходящую\" сладость...");
        Jellybean skittlesPops = new Jellybean("Skittles Rainbow");
        giftBox.addSweetsToTheBox(skittlesPops);
        giftBox.showBoxContent();

        System.out.println("\nДавайте добавим \"подходящую\" сладость...");
        Chocolate alenka = new Chocolate("ALENKA");
        giftBox.addSweetsToTheBox(alenka);
        giftBox.showBoxContent();

        System.out.println("\nОтключим policy и опять добавим \"неподходящую\" сладость...");
        giftBox.turnOffBoxPolicy();
        giftBox.addSweetsToTheBox(skittlesPops);
        giftBox.showBoxContent();

        System.out.println("\nДобавим ещё одну \"правильную\" сладость с включенным policy...");
        Chocolate bigMarsBar = new Chocolate("MARS XL");
        giftBox.addSweetsToTheBox(bigMarsBar);
        giftBox.showBoxContentWithPriceWeight();

        System.out.println("\nВключим другую policy и добавим несколько других \"неподходящих\" сладостей...");
        giftBox.turnOnBoxPolicy("Less than 100 RUR");
        Sweets lindtChocolate = new Chocolate("Lindt 99%", 100, 250,"тёмный");
        Sweets megaChups = new Lollipop("Mega Chupik", 220, 499,"XL");
        giftBox.addSweetsToTheBox(lindtChocolate);
        giftBox.addSweetsToTheBox(megaChups);
        giftBox.showBoxContent();

        System.out.println("\nПоменяем policy вручную и снова добавим несколько (ранее) \"неподходящих\" сладостей...");
        giftBox.setBoxPolicy(sweets -> sweets.getPrice() < 500);
        giftBox.addSweetsToTheBox(lindtChocolate);
        giftBox.addSweetsToTheBox(megaChups);
        giftBox.showBoxContentWithPriceWeight();

        //3. Реализация CostCalculator для подарочной коробки.

        System.out.println("\n3. Демонстрация работы CostCalculator.");
        System.out.println("\nДля следующего случая, вновь очистим нашу коробку и отключим policy...");
        giftBox.getSweetsInTheBox().clear();
        giftBox.turnOffBoxPolicy();
        giftBox.showBoxContent();

    }
}
