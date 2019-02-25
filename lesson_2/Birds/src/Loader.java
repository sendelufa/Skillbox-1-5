import Birds.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Project Birds
 * Created by Shibkov Konstantin on 19.12.2018.
 */

public class Loader {
    public static void main(String[] args) {

        ArrayList<Birds> arrBirds = new ArrayList<>();

        //создаем стаю птиц
        System.out.println("   === Создаем стаю птиц ===");
        createBirds(30, arrBirds);

        System.out.println("\n   === Обходим все классы и вызываем методы alphaAction() voice() getInfo() ===");
        //обходим все классы и вызываем методы
        for (Birds item : arrBirds) {
            //выполняем метод основного действия птицы, определяемые в родительских классах Flying и Flightless
            item.alphaAction();
            //выполняем методы из родительского класса Birds
            item.voice();
            item.getInfo();
            System.out.println("\n");
        }

        System.out.println("   === Сравниваем двух птиц по весу и выводим результат проверки ===");
        //сравниваем птиц по весу и выводим результат проверки
        int compare = arrBirds.get(0).compareTo(arrBirds.get(1));
        String compareAnswer = compare > 0 ? arrBirds.get(0).getBirdName() + " меньше весит, чем " + arrBirds.get(1).getBirdName() :
                compare < 0 ? arrBirds.get(1).getBirdName() + " меньше весит, чем " + arrBirds.get(0).getBirdName() :
                        arrBirds.get(1).getBirdName() + " равна по весу " + arrBirds.get(0).getBirdName();
        System.out.println(compareAnswer + "\n");

        System.out.println("   === Сортируем список от большего к меньшему и через цикл выводим вес всех птиц ===");
        //сортируем список от большего к меньшему
        Collections.sort(arrBirds);

        //выводим список птиц
        for (Birds item : arrBirds) {
            item.getInfo();
        }

    }

    //создаем стаю птиц
    private static void createBirds(int count, ArrayList<Birds> Birds) {
        for (int i = 1; i <= count; i++) {
            int type = (int) Math.round(1 + 3 * Math.random());
            if (type == 1) {
                Birds.add(new Ostrich("Страус " + i));
            } else if (type == 2) {
                Birds.add(new Hen("Курица " + i));
            } else if (type == 3) {
                Birds.add(new Sparrow("Воробей " + i));
            } else if (type == 4) {
                Birds.add(new Duck("Утка " + i));
            }
        }
    }
}
