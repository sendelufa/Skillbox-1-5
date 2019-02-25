/**
 * Project Birds
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Birds;

abstract public class Flying extends Birds {
    private int flyspeed;


    public Flying(String birdName, String singVoice, double weightKg, int flyspeed) {
        super(birdName, singVoice, weightKg);
        this.flyspeed = flyspeed;
    }

    //возвращаем скорость полета
    public int getFlyspeed() {
        return flyspeed;
    }

    //птица летит
    public void fly() {
        //перед полетом птица начинает разгон с прогулки
        super.walk();
        System.out.println(birdName + " полетела");
    }

    @Override
    public void alphaAction(){
           fly();
    }
}
