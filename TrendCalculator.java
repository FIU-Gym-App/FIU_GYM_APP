package app;
import java.util.Timer;

public class TrendCalculator  {
    final int GYM_LIMIT = 500;
    int numberOfChechedIn = GymUser.getNumberOfCheckedIn();

    public double TrendCalculator(){
        return numberOfChechedIn/GYM_LIMIT;
    }
}
