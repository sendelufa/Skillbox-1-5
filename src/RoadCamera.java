import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoadCamera
{
    public static void main(String[] args) throws IOException
    {
        //Параметры программы
        Integer maxOncomingSpeed = 30; // km/h
        Integer speedFineGrade = 31; // km/h //до 60 включительно штрафа нет
        Integer k = 2; //коэффициент штрафа
        Integer finePerGrade = k*500; // RUB
        Integer criminalSpeed = 180; // km/h



        //=============================================================

        System.out.println("Введите скорость автомобиля:");

        //Скорость автомобиля

        Integer oncomingSpeed = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());
        if(oncomingSpeed >= criminalSpeed)
        {
            System.out.println("Вызов полиции...");
        }
        else if(oncomingSpeed > maxOncomingSpeed)
        {
            Integer overSpeed = oncomingSpeed - maxOncomingSpeed;
            Integer gradesCount = overSpeed / speedFineGrade;
            Integer fine = finePerGrade * gradesCount;
            System.out.println("Сумма штрафа: " + fine);
        }
        else {
            System.out.println("Скорость не превышена");
        }
    }
}