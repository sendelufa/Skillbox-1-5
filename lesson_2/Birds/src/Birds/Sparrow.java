/**
 * Project Birds
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Birds;

public class Sparrow extends Flying {

    public Sparrow(String birdName) {
        super(birdName, "чик-чирик", 0.021 + 0.016 * Math.random(), 30);
    }
}
