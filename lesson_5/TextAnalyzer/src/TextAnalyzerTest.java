import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Project TextAnalyzer
 * Created by Shibkov Konstantin on 20.12.2018.
 */

public class TextAnalyzerTest {
    @Test
    public void testTextAnalyzer() {
        String testStr = "";
        TextAnalyzer testTextAnalyzer = new TextAnalyzer(testStr);
        Assert.assertNotNull(testTextAnalyzer);

    }

    @Test
    public void testTextAnalyzerNull() {
        String testStr = null;
        TextAnalyzer testTextAnalyzer = new TextAnalyzer(testStr);
        Assert.assertNotNull(testTextAnalyzer);

    }

    @Test
    public void testGetWordsNormalString() {
        //проверка на правильность парсера слов
        TextAnalyzer testTextAnalyzer = new TextAnalyzer("no no yes 123 no for if yes 345 true no");
        String[] expectedList = {"no", "no", "yes", "no", "for", "if", "yes",
                "true", "no"};
        //переводим ArrayList в массив
        String[] arrayGetWords = null;
        ArrayList<String> listOfStrings = testTextAnalyzer.getWords();
        // Создаем массив строк такого же размеры, что и входной ArrayList
        arrayGetWords = listOfStrings.toArray(new String[listOfStrings.size()]);
        Assert.assertEquals(Arrays.toString(expectedList), Arrays.toString(arrayGetWords));
    }

    @Test
    public void testGetWordsEmptyString() {
        //проверка на правильность пустого текста
        TextAnalyzer testTextAnalyzer = new TextAnalyzer("");
        ArrayList<String> expectedList = new ArrayList<>();
        Assert.assertEquals(expectedList, testTextAnalyzer.getWords());
    }

    @Test
    public void testGetWordsNumberString() {
        //проверка на правильность только не буквенных символов
        TextAnalyzer testTextAnalyzer = new TextAnalyzer("123");
        ArrayList<String> expectedList = new ArrayList<>();
        Assert.assertEquals(expectedList, testTextAnalyzer.getWords());


    }

    @Test
    public void testGetMostFrequentWord() {
        //проверка работы метода на нахождение самого частого слова
        String freqWord = new TextAnalyzer("no no yes 123 no for if yes 345 true no").getMostFrequentWord();
        String expected = "no";

        Assert.assertEquals(expected, freqWord);
    }

    @Test
    public void testGetMostFrequentWordMoreThanOne() {
        //если частых слов несколько  - метод должен отдавать первое встречающееся в тексте
        String freqWord = new TextAnalyzer("no no 123 yes yes").getMostFrequentWord();
        String expected = "no";

        Assert.assertEquals(expected, freqWord);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMostFrequentExceptionEmptyString() {
        //если отправляется пустая строка - получаем Exception
        String freqWord = new TextAnalyzer("").getMostFrequentWord();

    }

    @Test(expected = NullPointerException.class)
    public void testGetMostFrequentExceptionNullString() {
        //если отправляется null - получаем Exception
        String freqWord = new TextAnalyzer(null).getMostFrequentWord();

    }
}
