/**
 * Project Birds
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Birds;

abstract public class Flightless extends Birds {
    private int runSpeed;

    protected Flightless(String birdName, String singVoice, double weightKg, int runSpeed) {
        super(birdName, singVoice, weightKg);
        this.runSpeed = runSpeed;
    }

    //возвращаем скорость бега
    protected int getRunSpeed() {
        return runSpeed;
    }

    public void run() {
        //перед бегом птица начинает разгон с прогулки
        super.walk();
        System.out.println(birdName + " бежит");
    }

    @Override
    public void alphaAction() {
        run();
    }
}
