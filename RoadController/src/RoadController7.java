import core.Camera;
import core.Car;
import core.Police;
import core.WeightMeter;

public class RoadController7
{
    //Double passengerCarMaxWeight
    public static Double passengerCarMaxWeight = 3500.0; // kg
    //Integer passengerCarMaxHeight
    public static Integer passengerCarMaxHeight = 2000; // mm
    //- Уменьшить высоту пропускного пункта до 3,5 метров
    //Integer controllerMaxHeight
    public static Integer controllerMaxHeight = 3500; // mm
    //Integer passengerCarPrice
    public static Integer passengerCarPrice = 100; // RUB
    //Integer cargoCarPrice
    public static Integer cargoCarPrice = 250; // RUB
    //Integer vehicleAdditionalPrice
    public static Integer vehicleAdditionalPrice = 200; // RUB

    //Integer maxOncomingSpeed
    public static Integer maxOncomingSpeed = 60; // km/h
    //Integer speedFineGrade
    public static Integer speedFineGrade = 20; // km/h
    //Integer finePerGrade
    public static Integer finePerGrade = 500; // RUB
    //Integer criminalSpeed
    public static Integer criminalSpeed = 160; // km/h

    public static void main(String[] args)
    {
        //Integer i
        for(Integer i = 0; i < 10; i++)
        {
            Car car = Camera.getNextCar();
            System.out.println(car);
            System.out.println("Скорость: " + Camera.getCarSpeed(car) + " км/ч");



            /**
             * Проверка на наличие номера в списке номеров нарушителей
             */
            //Boolean policeCalled
            Boolean policeCalled = false;
            for(String criminalNumber : Police.getCriminalNumbers())
            {
                //String carNumber
                String carNumber = car.getNumber();
                if(carNumber.equals(criminalNumber)) {
                    policeCalled = true;
                    Police.call("автомобиль нарушителя с номером " + carNumber);
                    blockWay("не двигайтесь с места! За вами уже выехали!");
                    break;
                }
            }

            /**
             * Пропускаем автомобили спецтранспорта если они не в списке угона
             */
            if(car.isSpecial() && !policeCalled) {
                openWay();
                continue;
            }

            if(Police.wasCalled()) {
                continue;
            }

            /**
             * Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
             */
            //Integer carHeight
            Integer carHeight = car.getHeight();
            //Integer price
            Integer price = 0;
            if(carHeight > controllerMaxHeight)
            {
                blockWay("высота вашего ТС превышает высоту пропускного пункта!");
                continue;
            }
            else if(carHeight > passengerCarMaxHeight)
            {
                //Double weight
                Double weight = WeightMeter.getWeight(car);
                //Грузовой автомобиль
                if(weight > passengerCarMaxWeight)
                {
                    price = cargoCarPrice;
                    if(car.hasVehicle()) {
                        price = price + vehicleAdditionalPrice;
                    }
                }
                //Легковой автомобиль
                else {
                    price =  passengerCarPrice;
                }
            }
            else {
                price = passengerCarPrice;
            }

            /**
             * Проверка скорости подъезда и выставление штрафа
             */
            //Integer carSpeed
            Integer carSpeed = Camera.getCarSpeed(car);
            if(carSpeed > criminalSpeed)
            {
                Police.call("cкорость автомобиля - " + carSpeed + " км/ч, номер - " + car.getNumber());
                blockWay("вы значительно превысили скорость. Ожидайте полицию!");
                continue;
            }
            else if(carSpeed > maxOncomingSpeed)
            {
                //Integer overSpeed
                Integer overSpeed = carSpeed - maxOncomingSpeed;
                //Integer totalFine
                Integer totalFine = finePerGrade * (1 + overSpeed / speedFineGrade);
                System.out.println("Вы превысили скорость! Штраф: " + totalFine + " руб.");
                price = price + totalFine;
            }

            /**
             * Отображение суммы к оплате
             */
            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }

    }

    /**
     * Открытие шлагбаума
     */
    public static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    public static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}