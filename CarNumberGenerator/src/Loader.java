import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Shibkov Konstantin 04.02.2019
 */
public class Loader {

    private static int bufferSize = 500_000;
    private static char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    private static int threads = 16;
    private static PrintWriter[] writer = new PrintWriter[threads];
    private static ReentrantLock[] lock = new ReentrantLock[threads];
    //организация пула потоков для генерации списка транзакци в многопоточном режиме
    private static ExecutorService executor = Executors.newFixedThreadPool(threads);

    private static List<Future<?>> tasks = new ArrayList<>();

    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        for (int i = 0; i < writer.length; i++) {
            writer[i] = new PrintWriter("C:\\Users\\sendel\\Desktop\\1\\numbers" + i + ".txt");
        }
        for (int i = 0; i < lock.length; i++) {
            lock[i] = new ReentrantLock();
        }

        for (int number = 1; number < 300; number++) {
            queue.add(number);
            tasks.add(executor.submit(() -> generateLetters()));
        }

        for (; ; ) {
            if (queue.size() == 0) {
                executor.shutdown();


            }
            if (executor.isTerminated()) {

                for (PrintWriter w : writer) {
                    w.flush();
                    w.close();
                }
                break;
            }


        }
        System.out.println((System.currentTimeMillis() - start) + " ms");


    }


    private static synchronized void generateLetters() {
        StringBuffer builder = new StringBuffer();
        int number = queue.poll();
        String str;
        str = number < 10 ? "00" : "0";
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        if (builder.length() > bufferSize) {
                            boolean writeSuccess = false;
                            while (!writeSuccess) {
                                for (int i = 0; i < threads; i++) {
                                    if (lock[i].tryLock()) {
                                        lock[i].lock();
                                        writer[i].write(builder.toString());
                                        lock[i].unlock();
                                        builder = new StringBuffer();
                                        writeSuccess = true;
                                        break;
                                    }
                                }
                            }

                        }
                        builder.append(firstLetter);
                        builder.append(str);


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

        boolean writeSuccess = false;
        while (!writeSuccess) {
            for (int i = 0; i < writer.length; i++) {
                if (lock[i].tryLock()) {
                    lock[i].lock();
                    writer[i].write(builder.toString());
                    lock[i].unlock();
                    writeSuccess = true;
                    break;
                }
            }
        }


    }
}
