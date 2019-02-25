import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.UserContact;
import org.telegram.api.contacts.TLContacts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Project TelegramExperiments
 * Created by Shibkov Konstantin on 12.12.2018.
 */

public class Loader {

    public static void main(String[] args) throws IOException {


    BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));

        //Подключаемся к API телеграмм
        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.50:443", 0, "0");

        //Запрашиваем тел номер пользователя
        System.out.println("Введите номер телефона:");
        String userTel = cleanoutTel(reader.readLine().trim());

        //Проверяем пользователь имеет аккаунт телеграмм
        AuthCheckedPhone checkedPhone = bridge.authCheckPhone(userTel);

        //Отправляем проверочный код для пользователя с введеным номером телефона
        AuthSentCode sentCode = bridge.authSendCode(userTel);

        //печатаем PhoneCodeHash в консоль
        System.out.println(sentCode.getPhoneCodeHash());

        //ждем ввода смс кода в консоли
        AuthAuthorization  authorization = bridge.authSignIn(reader.readLine().trim());

        //получаем имя и фамилию юзера и выводим в консоли
        System.out.println("Пользователь: " + authorization.getUser());

        //получение списка контактов c телефонами
        ArrayList<UserContact> contacts = bridge.contactsGetContacts();
        for(UserContact item: contacts){
            System.out.println(item + " - " + item.getPhone());
        }

    }

    //функция очистки номера телефона от лишних символов
    public static String cleanoutTel(String tel) {
        return tel.replaceAll("[^0-9]", "");
    }
}
