/**
 * Project Furniture
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Furniture;

import java.util.ArrayList;

public class Table extends Furniture {
    //Максимальное кол-во стульев подставляемых к столу
    private int maxChairToTable;
    private ArrayList<Chair> ChairToTable = new ArrayList<>();

    public Table(short legs, short height, String materials, float price, int maxChairToTable) {
        super(legs, height, materials, price);
        this.maxChairToTable = maxChairToTable;
    }

    //подставляем стул к столу для обеденного комплекта
    public void addChair(Chair chair){
        if (ChairToTable.size() >= maxChairToTable){
            System.out.println("Комплект полный - больше стульев добавить нельзя");
        }
        else {
            ChairToTable.add(chair);
            System.out.println("Добавлен стул с ценой: " + chair.getPrice());
        }

    }

    //считаем сумму обеденного комплекта и выводим список входящий в комплект с ценой
    public void summOfKit(){
        float summ = 0.0F;
        System.out.println("\nВ комплект входят:");
        System.out.println("Стол стоимостью: " + getPrice());
        summ += getPrice();
        for (Chair chair : ChairToTable){
            System.out.println("Стул стоимостью: " + chair.getPrice());
            summ += chair.getPrice();
        }
        System.out.println("    Стоимость комплекта:" + summ);
    }
}
