import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
       /* В проекте StringExperiments внести изменения таким образом, чтобы суммы заработков каждого человека извлекались
        из текста регулярным выражением, а в конце программы рассчитывалась и распечатываль общая сумма заработка всех.
        Приложить zip-архив изменённого проекта StringExperiments.*/

        parseSumm(text);

        //несколько строк для теста
        parseSumm("Паша работал и заработал 9000 рублей, Леша трудился за 68250 рубля, а Маша - молодец и получила 85241 рублей");
        parseSumm("У нас было несколько портфелей акций: Сбербанк - 250000 руб, Роснефть - 356000 рублей и Аэрофлот - 860000 рублей");

    }

    public static void parseSumm(String txt){
        int summ = 0; // сумма всех чисел в строке

        Pattern pattern = Pattern.compile("\\s*([0-9]+)\\s*");
        Matcher matcher = pattern.matcher(txt);
        while (matcher.find()) {
            summ += Integer.parseInt(matcher.group().trim());
        }
        System.out.println(txt);
        System.out.println("Сумма:" + summ + "\n");
    }
}