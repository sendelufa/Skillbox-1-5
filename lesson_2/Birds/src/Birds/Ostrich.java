/**
 * Project Birds
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Birds;

public class Ostrich extends Flightless {

    public Ostrich(String birdName) {
        super(birdName, "у-у-уууу у-у-ууу у-у-ууу", 102 + 54 * Math.random(), 65);
    }
}
