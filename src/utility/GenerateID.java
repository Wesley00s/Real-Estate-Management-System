package utility;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateID
{
    private static final Set<Integer> idSet = new HashSet<>();

    public static String ID()
    {
        Random random = new Random();
        int numId;
        do
        {
            numId = random.nextInt(100000000, 1000000000);
        }
        while (!idSet.add(numId));

        return String.valueOf(numId);
    }
}