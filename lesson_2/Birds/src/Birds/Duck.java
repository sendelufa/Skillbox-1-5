/**
 * Project Birds
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Birds;

public class Duck extends Flying {

    public Duck(String birdName) {
        super(birdName, "кря-кря", 3 + 4 * Math.random(), 40);
    }
}
