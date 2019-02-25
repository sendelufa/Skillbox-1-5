/**
 * Project Comparison
 * Created by Shibkov Konstantin on 10.12.2018.
 */

public class Loader {
    public static void main(String[] args) {
     Integer dimaAge = 40;
     Integer mishaAge = 50;
     Integer vasyaAge = 70;

     Integer oldest;
     Integer youngest;
     Integer middle;

     // определяем самый старший возвраст
     oldest = (dimaAge > mishaAge && dimaAge > vasyaAge) ? dimaAge : (mishaAge > vasyaAge) ? mishaAge : vasyaAge;

     // определяем самый младший возвраст
     youngest = (dimaAge < mishaAge && dimaAge < vasyaAge) ? dimaAge : (mishaAge < vasyaAge) ? mishaAge : vasyaAge;

     // определяем средний возвраст
     middle = ((dimaAge < mishaAge && dimaAge > vasyaAge) || (dimaAge > mishaAge && dimaAge < vasyaAge)) ? dimaAge :
             ((mishaAge < dimaAge && mishaAge > vasyaAge) || (mishaAge > dimaAge && mishaAge < vasyaAge)) ? mishaAge :
             ((vasyaAge < mishaAge && vasyaAge > dimaAge) || (vasyaAge > mishaAge && vasyaAge < dimaAge)) ? vasyaAge : -1;

     System.out.println("Most old: " + oldest);
     System.out.println("Most young: " + youngest);

     //если есть средний возвраст - выводим, если нет - пишем что такого не найден
        String txtMiddle = (middle != -1) ? "Middle: " + middle : "Middle: there is no middle age ";
     System.out.println(txtMiddle);


    }
}
