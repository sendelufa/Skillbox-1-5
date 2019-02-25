/**
 * Project Birds
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Birds;

abstract public class Birds implements Comparable<Birds>{

    //вес птицы
    protected double weightKg;
    protected String singVoice;
    protected String birdName;

    //конструктор
    public Birds(String birdName, String singVoice, double weightKg) {
        this.birdName = birdName;
        this.weightKg = weightKg;
        this.singVoice = singVoice;
        System.out.println(birdName + " создан!");
    }

    //подаем голос
    public void voice() {
        System.out.println(birdName + " говорит " + singVoice);
    }

    //выводим информацию о созданной птице
    public void getInfo(){
        System.out.println(getBirdName() + ": Вес=" + getWeightKg() + "кг");
    }

    //возвращаем вес птицы
    public double getWeightKg() {
        return weightKg;
    }

    //возвращаем имя птицы
    public String getBirdName() {
        return birdName;
    }

    //метод выполняет главное действие класса, для летающих летать, для нелетающих - бегать,
    //кдасс абстрактный, т.к каждый тип птицы будет сам определять что для него главное действие
    abstract public void alphaAction();

    //все птицы могут ходить, заставить птицу ходить мы не можем
    protected void walk(){
        System.out.print(birdName + " идет => ");
    }

    //реализуем метод compareTo для интерфейса Comparable<Birds>
    @Override
    public int compareTo(Birds bird){
        return (int) Math.round(bird.getWeightKg() - this.getWeightKg());
    }


}
