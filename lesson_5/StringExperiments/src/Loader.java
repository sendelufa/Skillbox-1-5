public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        //С помощью методов indexOf(), lastIndexOf(), substring() и trim()
        // написать код в проекте StringExperiments, который считает сумму заработка Васи и Маши.

        String currency = "руб";

        int summ = getIncomeFromName(text, "Вася", currency) + getIncomeFromName(text, "Маша", currency);
        System.out.println("--\nСумма: " + summ + "\n");

        summ = getIncomeFromName(text, "Вася", currency) + getIncomeFromName(text, "Петя", currency);
        System.out.println("--\nСумма: " + summ + "\n");

    }

    public static Integer getIncomeFromName(String txt, String name, String currency) {
        //Поиск вхождения имени в строке
        int positionOfName = txt.indexOf(name);

        //ищем первое после имени вхождение валюты "руб" и вырезаем фрагмент от имени до валюты, доп. вырезаем пробелы
        String incomeTxt = txt.substring(positionOfName, txt.indexOf(currency, positionOfName)).trim();

        int incomeInt = Integer.parseInt(incomeTxt.substring(incomeTxt.lastIndexOf(' ')).trim());
        //ищем последний пробел и все что после пробела и до конца строки - сумма, доп. вырезаем пробелы
        System.out.println(name + " - " + incomeInt);

        return incomeInt;
    }
}