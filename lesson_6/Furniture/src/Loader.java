import Furniture.Chair;
import Furniture.Table;

/**
 * Project Furniture
 * Created by Shibkov Konstantin on 19.12.2018.
 */

public class Loader {
    public static void main(String[] args) {
        //создаем стол
        Table table = new Table((short)4, (short)750, "Дубовый", 35000f, 6);

        //Добавляем и создаем стулья в комплект
        //попробуем добавлять стулья до максимума и больше
        table.addChair(new Chair((short)4, (short)550, "Фанера", 5000f));
        table.addChair(new Chair((short)2, (short)550, "Дуб", 500f));
        table.addChair(new Chair((short)3, (short)550, "Бук", 1000f));
        table.addChair(new Chair((short)5, (short)550, "Ясень", 10000f));
        table.addChair(new Chair((short)4, (short)550, "МДФ", 15000f));
        table.addChair(new Chair((short)4, (short)550, "Камень", 3000f));
        table.addChair(new Chair((short)4, (short)550, "Фанера", 5000f));

        table.summOfKit();

    }
}
