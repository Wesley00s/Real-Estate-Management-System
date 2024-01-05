package utility;

public class Attempts {
    public static final int TOTAL_ATTEMPTS = 4;
    public static boolean chances(int chances) {
        if(chances <= 0) {
            System.out.println("Max attempts reached. Cancelling...");
            return true;
        }
        return false;
    }
}
