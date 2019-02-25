
public class Loader
{
    public static void main(String[] args)
    {
        //Создание котов и кошек

        Cat frank = new Cat();
        Cat smoke = new Cat();
        Cat mingan = new Cat();
        Cat narg = new Cat();
        Cat melinda = new Cat();
        Cat martin = new Cat();
        Cat hank = new Cat();

        //Смотрим вес
        System.out.println("Вес созданных котов и кошек:");
        System.out.println(frank.getWeight());
        System.out.println(smoke.getWeight());
        System.out.println(mingan.getWeight());
        System.out.println(narg.getWeight());
        System.out.println(melinda.getWeight());
        System.out.println(martin.getWeight());
        System.out.println(hank.getWeight());

        //Кормим
        System.out.println("\nКормим животных!");
        frank.feed(225.0);
        smoke.feed(525.0);
        mingan.feed(125.0);
        narg.feed(26.3);
        melinda.feed(2011.5);
        martin.feed(425.2);
        hank.feed(15.5);

        //Смотрим вес и состояние после кормления
        System.out.println("\nВес и состояние после кормления:");
        System.out.println(frank.getWeight() + " : " + frank.getStatus());
        System.out.println(smoke.getWeight() + " : " + smoke.getStatus());
        System.out.println(mingan.getWeight() + " : " + mingan.getStatus());
        System.out.println(narg.getWeight() + " : " + narg.getStatus());
        System.out.println(melinda.getWeight() + " : " + melinda.getStatus());
        System.out.println(martin.getWeight() + " : " + martin.getStatus());
        System.out.println(hank.getWeight() + " : " + hank.getStatus());

        //взрываем smoke
        System.out.println("\nЛопаем smoke:");
        smoke.feed(9000.0);
        System.out.println(smoke.getStatus());


        //Замяукаем до смерти кота frank
        System.out.println("\nЗамяукаем до смерти frank:");
        do {
            frank.meow();
            System.out.println("Status:" + frank.getStatus() + "\tweight:" + frank.getWeight());
        } while(!frank.getStatus().equals("Dead"));

    }
}