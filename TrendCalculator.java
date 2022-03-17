package app;
import java.util.Timer;

public class TrendCalculator  {
    final int GYM_LIMIT = 500;
    int numberOfChechedIn = getNumberOfCheckedIn();

    public double TrendCalculator(int numberOfChechedIn){
        return numberOfChechedIn/GYM_LIMIT;
    }
}