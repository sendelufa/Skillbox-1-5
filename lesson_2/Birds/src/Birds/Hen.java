/**
 * Project Birds
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Birds;

public class Hen extends Flightless {

    public Hen(String birdName) {
        super(birdName, "ко-ко-ко", 1 + 4 * Math.random(), 10);
    }
}
