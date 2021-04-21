import java.util.Random;

public class RandomPriority {

    public static double random() {
        Random random = new Random();


       double num;
        do{
            num = random.nextGaussian();
        }while (num < 0.0 || num > 1.0);

        return num;
    }


}
