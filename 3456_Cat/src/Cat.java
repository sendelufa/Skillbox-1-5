public class Cat {
    private Double originWeight;
    private Double weight;

    private Double minWeight;
    private Double maxWeight;

    //переменная суммы съеденного корма - Урок 3
    private Double feedAllTime;

    //статическая переменная общего количества созданных котов
    private static Integer catsCount = 0;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        feedAllTime = 0.0;

        //Добавляем 1 к количеству созданных котов
        catsCount++;
    }
    /*
    Урок - 5 - Создание конструктора с аргументом
     */
    public Cat(Double startWeight) {
        this();
        weight = startWeight;
    }
     /*
    Урок - 5 - Создание конструктора с аргументом
     */

    /*
    Урок - 6 - Копирование объектов
    */
    public Cat makeCopy(Cat cat){
        this.weight = cat.getWeight();
        this.originWeight = cat.getOriginWeight();
        this.feedAllTime = cat.getFeedAllTime();
        return cat;
    }

    public Double getOriginWeight() {
        return originWeight;
    }

    /*
       Урок - 6 - Копирование объектов
    */


    public void meow() {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void meow(Double weight) {
        this.weight -= weight;
        System.out.println("Meow");
    }

    public static Double getWeightDifference(Cat cat1, Cat cat2) {
        Double difference = Math.abs(cat1.getWeight() - cat2.getWeight());
        return difference;
    }

    public void feed(Double amount) {
        weight = weight + amount;
        feedAllTime += amount;

    }

    public void drink(Double amount) {
        weight = weight + amount;
    }

    public Double getWeight() {
        return weight;
    }

    /*
      Урок - 3 - Метод, параметры, return
   */

    //возвращаем количество съеденного корма
    public Double getFeedAllTime() {
        return feedAllTime;
    }

    //метод Сходить в туалет
    public void goWC() {
        weight = weight - 50;
        System.out.println("Рee-Рее");
    }

    /*
      Урок - 3 - Метод, параметры, return
   */

    //Выводим количество живых кошек
    public static int getCount() {
        return catsCount;
    }

    public String getStatus() {
        if (weight < minWeight) {
            //уменьшаем количество кошек
            catsCount--;
            return "Dead";
        } else if (weight > maxWeight) {
            //уменьшаем количество кошек
            catsCount--;
            return "Exploded";

        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}