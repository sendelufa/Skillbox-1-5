import java.io.PrintWriter;

/**
 * Created by Shibkov Konstantin 04.02.2019
 */
public class LoaderOneTh {
    private static PrintWriter writer;

    private static StringBuffer builder = new StringBuffer();
    private static int bufferSize = 500_000;
    private static char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        writer = new PrintWriter("res/numbers.txt");

        for (int number = 1; number < 300; number++) {
            for (int regionCode = 1; regionCode < 100; regionCode++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            if (builder.length() > bufferSize) {
                                writer.write(builder.toString());
                                builder = new StringBuffer();
                            }
                            builder.append(firstLetter);
                            if (number < 10) {
                                builder.append("00");
                            } else if (number < 100) {
                                builder.append("0");
                            }

                            builder.append(number)
                                    .append(secondLetter)
                                    .append(thirdLetter);
                            if (regionCode < 10) {
                                builder.append("0");
                            }
                            builder.append(regionCode)
                                    .append("\n");
                        }
                    }
                }
            }
        }
        writer.write(builder.toString());
        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

}
