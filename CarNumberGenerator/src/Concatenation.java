/**
 * Created by Danya on 20.02.2016.
 */
public class Concatenation
{
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();

        StringBuilder str = new StringBuilder();
        for(int i = 0; i < 50_000; i++)
        {
            str.append("some text some text some text");
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
