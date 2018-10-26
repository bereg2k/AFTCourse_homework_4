import factory.LollipopFactory;
import factory.SweetsFactory;
import sweets.Chocolate;
import sweets.Jellybean;
import sweets.Lollipop;
import sweets.Sweets;

/**
 * Задание "Упаковка подарка" - демонстрация возможностей Java 8.
 * 1) Реализовать функциональный интерфейс фабрики сладостей
 * 2) Реализовать policy для коробочки
 * 3) Реализовать внутри коробочки что-то типа CostCalculator
 * 4) Добавить в коробочку несколько методов,  внутри использующих Stream API
 * 5) Для более симпатичной реализации классов самих сладостей, добавить ENUM’ы.
 *
 * @author Oleg Berezhnoy
 */
public class Gift {
    public static void main(String[] args) {

        GiftBox giftBox = new GiftBox(); //создаём новую подарочную коробку

        //1. Создать фабрику по производству сладостей.
        //создание объекта класса с помощью лямбда-выражения
        System.out.println("\n1. Демонстрация работы фабрик по производству сладостей");

        System.out.println("\nДобавим немного шоколадных батончиков...");
        SweetsFactory marsBars = name -> new Chocolate(name, 80, 70, String.valueOf(Chocolate.ChocolateTypes.WHITE));
        giftBox.addSweetsToTheBox(marsBars.createSweets("MARS XL Whitey"));
        giftBox.addSweetsToTheBox(marsBars.createSweets("MARS Peanuts Arctic"));
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

        String policyChocolate = "ONLY CHOCOLATE";
        System.out.println("\nЗадействуем policy (" + policyChocolate + ") для добавления сладостей в коробку...");
        giftBox.turnOnBoxPolicy(policyChocolate);

        System.out.println("\nДавайте добавим \"неподходящую\" сладость...");
        Jellybean skittlesPops = new Jellybean("Skittles Rainbow");
        giftBox.addSweetsToTheBox(skittlesPops);
        giftBox.showBoxContent();

        System.out.println("\nДавайте добавим \"подходящую\" сладость...");
        Chocolate alenka = new Chocolate("ALENKA");
        giftBox.addSweetsToTheBox(alenka);
        giftBox.showBoxContent();

        System.out.println("\nОтключим policy \"" + policyChocolate + "\" и опять добавим \"неподходящую\" сладость...");
        giftBox.turnOffBoxPolicy();
        giftBox.addSweetsToTheBox(skittlesPops);
        giftBox.showBoxContent();

        System.out.println("\nДобавим ещё одну \"правильную\" сладость с включенным policy \"" + policyChocolate + "\"...");
        Chocolate bigMarsBar = new Chocolate("MARS XL");
        giftBox.addSweetsToTheBox(bigMarsBar);
        giftBox.showBoxContent();

        String policyLessThan = "LESS THAN 100 RUB";
        System.out.println("\nВключим другую policy (" + policyLessThan + ") и добавим несколько других \"неподходящих\" сладостей...");
        giftBox.turnOnBoxPolicy(policyLessThan);
        Sweets lindtChocolate = new Chocolate("Lindt 99%", 100, 250, String.valueOf(Chocolate.ChocolateTypes.DARK));
        Sweets megaChups = new Lollipop("Mega Chupik", 220, 499, String.valueOf(Lollipop.LollipopSizes.XL));
        giftBox.addSweetsToTheBox(lindtChocolate);
        giftBox.addSweetsToTheBox(megaChups);
        giftBox.showBoxContent();

        System.out.println("\nПоменяем policy вручную и снова добавим несколько (ранее) \"неподходящих\" сладостей...");
        giftBox.setBoxPolicy(sweets -> sweets.getPrice() < 500);
        giftBox.addSweetsToTheBox(lindtChocolate);
        giftBox.addSweetsToTheBox(megaChups);
        giftBox.showBoxContentWithPriceWeight();

        //3. Реализация CostCalculator для подарочной коробки.

        System.out.println("\n3. Демонстрация работы CostCalculator (конвертация валют для цены).");
        System.out.println("\nДля следующего случая отключим policy для коробки и включим конвертацию валют в ЕВРО...");
        giftBox.turnOffBoxPolicy();
        giftBox.setPriceConverted(true);
        giftBox.setCurrency(String.valueOf(GiftBox.Currency.EUR));
        giftBox.showBoxContentWithPriceWeight();

        System.out.println("\nА теперь включим конвертацию валют в ДОЛЛАРЫ США...");
        giftBox.setCurrency(String.valueOf(GiftBox.Currency.USD));
        giftBox.showBoxContentWithPriceWeight();

        System.out.println("\nВыключим конвертацию валют совсем...");
        giftBox.setPriceConverted(false);
        giftBox.showBoxContentWithPriceWeight();

        //4. Демонстрация методов Stream API
        System.out.println("\n4. Демонстрация работы методов Stream API.");

        System.out.println("\nНа текущий момент в коробке находятся следующие экземпляры классов: ");
        giftBox.getClassNamesOfSweets();

        System.out.println("\nКоличество сладостей каждого типа следующее: ");
        giftBox.countSweetsTypeInTheBox();

        double limitPrice = 81.49;
        System.out.println("\nCладости не дороже " + limitPrice + " руб. вот такие: ");
        giftBox.getCheapSweets(limitPrice);

        System.out.println("\nСпасибо за просмотр демонстрации возможностей Java 8! Всех благ!");
    }
}
