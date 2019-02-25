import java.io.*;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sendel.ConsolePathReader;

/**
 * Project Contacts
 * Created by Shibkov Konstantin on 05.01.2019.
 */

public class Loader {

    static Pattern patternTel = Pattern.compile("^[0-9]{11}$");
    static Pattern patternLIST = Pattern.compile("^LIST$");
    static Pattern patternGenerate = Pattern.compile("^GENERATE$");
    static Pattern patternExport = Pattern.compile("^EXPORT$");

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String exportFile = "/JSON.json";

    // Ключ - ФИО, Значение - телефон
    static TreeMap<String, String> Contacts = new TreeMap<>();

    public static void main(String[] args) throws IOException {
      /* TODO - Написать умный эмулятор телефонной книги. Если в неё ввести новое имя, она должна запросить номер
            телефона. Если в неё ввести новый номер телефона, должна запросить имя. Если введённое имя или номер
            телефона найдены, должна вывести дополнительную информацию: номер или имя, соответственно. Команда LIST
            должна выводить всех абонентов в алфавитном порядке с номерами телефонов.*/

        String input;

        Generate();
        System.out.println("Генерация контакт листа выполнено успешно!");
        System.out.println("Вывод списка:");
        printLIST();
        System.out.println("\nЭкспорт файла в JSON:");
        ExportToJSON();

        for (; ; ) {
            System.out.println("\nВведите номер (11 цифр) / Имя контакта / LIST / EXPORT(JSON):");
            input = reader.readLine().trim();

            //обработка вывода списка
            if (isLIST(input)) {
                printLIST();
            } else if (isGenerate(input)) {
                Generate();
            } else if (isExport(input)) {
                ExportToJSON();
            }
            //обработка при определении того что введен номер телефона
            else if (isNumber(input)) {
                //если номер есть в Tree - выводим Контакт
                if (Contacts.containsKey(input)) {
                    System.out.println("Контакт: " + Contacts.get(input));

                }
                //если номера нет в Tree - запрашиваем ввод имени
                else {
                    writeNameToTree(input);
                }

            }
            //если введен не LIST и не номер телефона - значит это Имя контакта
            else {
                //если Контакт есть в Tree - выводим все номера приналежащие контакту
                if (Contacts.containsValue(input)) {
                    System.out.println("Контакт: " + input);
                    for (String item : Contacts.keySet()) {
                        if (Contacts.get(item).equals(input)) {
                            System.out.println(" -> " + item);
                        }
                    }

                }
                //если контакта нет в Tree - запрашиваем ввод номера
                else {
                    writeNumberToTree(input);
                }

            }
        }

    }

    //Проверка строки на соответствие международному формату тел номеров
    private static boolean isNumber(String input) {
        Matcher match = patternTel.matcher(input.replaceAll("[^0-9]", ""));
        return match.find();
    }

    //проверка, если строка не ссответвует критериям номера, значит это имя контакта
    private static boolean isName(String input) {
        return !(isNumber(input));
    }

    //проверка, введена ли команда LIST
    private static boolean isLIST(String input) {
        Matcher match = patternLIST.matcher(input);
        return match.find();
    }

    //проверка, введена ли команда GENERATE
    private static boolean isGenerate(String input) {
        Matcher match = patternGenerate.matcher(input);
        return match.find();
    }

    //проверка, введена ли команда EXPORT
    private static boolean isExport(String input) {
        Matcher match = patternExport.matcher(input);
        return match.find();
    }

    //для проверки кода, заполняется TreeMap тестовыми значениями
    private static boolean Generate() {
        Contacts.put("79991234567", "Константин");
        Contacts.put("79997654321", "Павел");
        Contacts.put("79999999999", "Константин");
        Contacts.put("78888888888", "Маша");
        Contacts.put("77772377777", "Анастасия");
        Contacts.put("78667766666", "Павел");
        Contacts.put("75121125555", "Константин");
        Contacts.put("78888568888", "Маша");
        Contacts.put("77777777777", "Анастасия");
        Contacts.put("76666666666", "Павел");
        Contacts.put("75555555555", "Константин");
        Contacts.put("77244777777", "Олег");
        Contacts.put("76623442666", "John");
        Contacts.put("75521254224", "Оля");
        return true;
    }

    //Записываем Имя в TreeMap, ключ - телефон
    private static void writeNameToTree(String phoneNumber) throws IOException {
        String Name;
        do {
            System.out.println("\nВведите Имя контакта для номера " + phoneNumber + ":");
            Name = reader.readLine().trim();
        } while (!isName(Name));
        Contacts.put(phoneNumber, Name);
        System.out.println("Добавлена запись! контакт:" + Name + ", номер:" + phoneNumber);
    }

    //Записываем Номер телефона в TreeMap, ключ - телефон
    private static void writeNumberToTree(String contactName) throws IOException {
        String phoneNumber;
        do {
            System.out.println("\nВведите номер телефона для " + contactName + ":");
            phoneNumber = reader.readLine().trim().replaceAll("[^0-9]", "");
            phoneNumber = isContainsPhoneNumber(phoneNumber);
        } while (!isNumber(phoneNumber));
        Contacts.put(phoneNumber, contactName);
        System.out.println("Добавлена запись! контакт:" + contactName + ", номер:" + phoneNumber);
    }

    //печатаем список контактов
    private static void printLIST() {
        for (String item : Contacts.keySet()) {
            System.out.println(item + " -> " + Contacts.get(item));
        }
    }

    //проверяем существует ли телефон в коллекции, если да, то предлагаем заменить запись
    private static String isContainsPhoneNumber(String phoneNumber) throws IOException {
        if (Contacts.containsKey(phoneNumber)) {
            System.out.println("Этот номер телефона записан у контакта \"" + Contacts.get(phoneNumber) + "\"");
            String confirmation;
            for (; ; ) {
                System.out.println("Перезаписать контакт? да/нет");
                confirmation = reader.readLine().trim().toLowerCase();
                if (confirmation.equals("нет")) {
                    System.out.println("Контакт не перезаписан!");
                    return "NaN";
                } else if (confirmation.equals("да")) {
                    System.out.println("Контакт перезаписан!");
                    break;
                }
            }
        }
        return phoneNumber;

    }

    private static void ExportToJSON() throws IOException {
        //Запрашиваем путь папки для вывода файла
        String path = ConsolePathReader.getDirPathFromConsole("Введите папку для сохранения списка контактов в JSON формате") + exportFile;

        JSONObject objectJSON = new JSONObject(); //корневой объект
        JSONObject contactList = new JSONObject(); //ячейки списка
        objectJSON.put("contacts", contactList);

        //обходим TreeMap и внедряем информацию в JSON объект,
        //если у Контакта несколько номеров - перечисляем в поле значение через точку с запятой
        for (String key : Contacts.keySet()) {
            if (contactList.get(Contacts.get(key)) != null) {
                contactList.put(Contacts.get(key), contactList.get(Contacts.get(key)) + ";" + key);
            } else {
                contactList.put(Contacts.get(key), key);
            }
        }

        //Запись результата в файл
        try (OutputStream os = new FileOutputStream(path, false)) {
            Writer writer = new OutputStreamWriter(os);
            writer.write(objectJSON.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }
}


