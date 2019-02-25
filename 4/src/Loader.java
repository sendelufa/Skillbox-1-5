
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();

        //выводим вес кота
        System.out.println("Вес кота:" + cat.getWeight());

        //кормим кота
        cat.feed(100.0);
        cat.feed(200.0);

        //выводим количество съеденной еды
        System.out.println("Количество съеденной еды:" + cat.getFeedAllTime());

        //кот идет в туалет
        cat.goWC();

        //выводим вес кота
        System.out.println("Вес кота:" + cat.getWeight());

        System.out.println(cat.getStatus());
    }
}