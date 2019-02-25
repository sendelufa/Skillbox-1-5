import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project EngText
 * Created by Shibkov Konstantin on 15.12.2018.
 */

public class Loader {
    public static void main(String[] args) {
        /*Возьмите английский текст (не менее 100 слов) и напишите программу, которая будет разбивать его на слова
         и печатать слова в консоли. Приложить zip-архив проекта.*/
        String text = "With the available reserves of crude oil in the world constantly diminishing, the communist " +
                "government of the People's Republic of China declared war on the United States, invading Alaska" +
                " for the few remaining sources of oil there. The Sino-American War raged for eleven years, " +
                "eventually culminating in a nuclear exchange between China and the United States. Both nations" +
                " had built up huge stockpiles of nuclear weapons, and the mutual attack drew in enemies and " +
                "allies from all around the world, igniting every hatred forged throughout the century-long cold" +
                " war. Although the conflict lasted only two hours, the destruction it brought was staggering " +
                "and complete. More energy was released in the early moments of the exchange than in all previous" +
                " global conflicts combined. This global nuclear conflict came to be known as the \"Great War\".";

        //добавлены символы ' -, основываясь на расчете слов Microsoft Word
        Pattern pattern = Pattern.compile("([a-zA-Z'-]+)");
        Matcher matcher = pattern.matcher(text);
        int i = 0;
        while (matcher.find()) {
            System.out.println(matcher.group());
            i++;
        }
        System.out.println("Всего слов: " + i);


    }
}
