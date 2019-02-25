
public class Loader {
    public static void main(String[] args) {
        Cat cat = new Cat();
        //Создадим еще 4 кота
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        cat5.feed(12.2);

        //выводим вес кота
        System.out.println("Вес кота:" + cat.getWeight());

        //кормим кота
        cat.feed(100.0);
        cat.feed(200.0);

        /*
      Урок - 3 - Метод, параметры, return
   */
        //выводим количество съеденной еды
        System.out.println("\nУрок 3 - Метод, параметры, return");

        System.out.println("Количество съеденной еды:" + cat.getFeedAllTime());

        //кот идет в туалет
        cat.goWC();

        //выводим вес кота
        System.out.println("Вес кота:" + cat.getWeight());

        System.out.println(cat.getStatus());

        /*
      Урок - 3 - Метод, параметры, return
   */

        //Урок 4 - Сравнение веса кошек
        System.out.println("\n\nУрок 4 - Сравнение веса кошек");
        System.out.println("cat2: " + cat2.getWeight());
        System.out.println("cat3: " + cat3.getWeight());
        System.out.println("Diff Abs (cat2-cat3):" + Cat.getWeightDifference(cat2, cat3));

        /*
        Начало > Урок 4 - Домашнее задание - Работа с количеством котов
        */
        System.out.println("\nУрок 4 - Работа с количеством котов\nКоличество живых котов:" + Cat.getCount());


        System.out.println("Количество живых котов:" + Cat.getCount());

        //cat2 закормим, кормим так чтобы превысить макс вес
        cat2.feed(9000.0);
        //проверим статус кота
        //Если неодкратно проверять статус, каждый раз количество котов будет уменьшаться
        System.out.println(cat2.getStatus());


        System.out.println("Количество живых котов:" + Cat.getCount());
        /*
        Конец > Урок 4 - Домашнее задание - Работа с количеством котов
        */

        /*
         Урок 5 - Домашнее задание - Работа с количеством котов
        */

        System.out.println("\n\n= Урок - 5 - Создание метода getKitten, который создает котенка с весом 100-200 грамм");
        Cat kitten = getKitten();
        System.out.println(kitten.getWeight());
        /*
        Урок - 5 - Создание объектов и конструктор
        */
        /*
            Урок - 6 - Копирование объектов
            Создаем новый объект CopyСat и копируем параметры из cat5
        */
        System.out.println("\n\n= Урок - 6 - Копирование объектов\n" +
                "Создаем новый объект CopyСat и копируем параметры из cat5");
        //создаем нового кота и копируем в него свойства из cat5
        Cat CopyCat = new Cat();

        //стартовые параметры CopyCat & cat5
        System.out.println("\n1) стартовые параметры\nвес CopyCat:\n     " + CopyCat.getWeight() + "/" + CopyCat.getFeedAllTime() + "/" + CopyCat.getStatus());
        System.out.println("вес cat5:\n     " + cat5.getWeight() + "/" + cat5.getFeedAllTime() + "/" + cat5.getStatus());

        CopyCat.makeCopy(cat5);

        //параметры CopyCat & cat5 после глубокого копирования
        System.out.println("\n2) параметры после глубокого копирования\nвес CopyCat:\n     " + CopyCat.getWeight() + "/" + CopyCat.getFeedAllTime() + "/" + CopyCat.getStatus());
        System.out.println("вес cat5:\n     " + cat5.getWeight() + "/" + cat5.getFeedAllTime() + "/" + cat5.getStatus());

        //изменяем cat5
        cat5.feed(100.0);
        cat5.feed(10000.0);

        //проверяем параметры CopyCat & cat5 после кромления cat5, параметры CopyCat не изменились
        System.out.println("\n3) после кормления cat5\nвес CopyCat:\n     " + CopyCat.getWeight() + "/" + CopyCat.getFeedAllTime() + "/" + CopyCat.getStatus());
        System.out.println("вес cat5:\n     " + cat5.getWeight() + "/" + cat5.getFeedAllTime() + "/" + cat5.getStatus());



    }

    /*
    Урок - 5 - Создание объектов и конструктор
    */

    public static Cat getKitten() {
        Cat kitten = new Cat(100.0 + Math.random() * 100);
        return kitten;
    }

    /*
    Урок - 5 - Создание объектов и конструктор
    */




}