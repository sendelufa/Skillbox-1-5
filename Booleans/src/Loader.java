
public class Loader
{
    public static void main(String[] args)
    {
        Integer milkAmount = 500; // ml
        Integer powderAmount = 500; // g
        Integer eggsCount = 5; // items
        Integer sugarAmount = 20; // g
        Integer oilAmount = 30; // ml
        Integer appleCount = 8;

        //powder - 400 g, sugar - 10 g, milk - 1 l, oil - 30 ml
        if(powderAmount >= 400 && sugarAmount >= 10 && milkAmount >= 1000 && oilAmount >= 30) {
            System.out.println("Pancakes");
        }

        //milk - 300 ml, powder - 5 g, eggs - 5
        if(milkAmount >= 300 && powderAmount >= 5 &&  eggsCount >= 5) {
            System.out.println("Omelette");
        }

        //apples - 3, milk - 100 ml, powder - 300 g, eggs - 4
        if(appleCount >= 3 && milkAmount >= 100 && powderAmount >= 300 && eggsCount >= 4) {
            System.out.println("Apple pie");
        }
    }
}