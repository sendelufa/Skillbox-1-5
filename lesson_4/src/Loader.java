import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Project lesson4
 * Created by Shibkov Konstantin on 15.12.2018.
 */

public class Loader {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите кол-во ящиков:");
        //кол-во ящиков
        int boxes = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());

        int i = 0;
        int trucks = 0; //кол-во грузовиков
        int containers = 0; //кол-во контейнеров

        int contInTruck = 12; //вместимость контейнеров в грузовик
        int boxesInContainer = 27; //вместимость ящиков в контейнер

        while (i < boxes) {
            //Пример: через каждые  324 (12*27) ящиков грузим в новый грузовик
            if (i % (boxesInContainer * contInTruck) == 0) {
                trucks++;
                System.out.println("Грузовик " + trucks + ":");
            }
            if (i % boxesInContainer == 0) {
                containers++;
                System.out.println("\tКонтейнер " + containers + ":");
            }
            i++;
            System.out.println("\t\t\tЯщик " + i);
        }
        System.out.println("== Результат ==");
        System.out.println("для упаковки и отправки " + boxes + " ящика/ов требуется:");
        System.out.println(" - " + trucks + " грузовика/ов");
        System.out.println(" - " + containers + " контейнера/ов");
    }
}
