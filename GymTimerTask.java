package app;
import java.util.Timer;
import java.util.TimerTask;

public class GymTimerTask extends TimerTask{

    Timer timer = new Timer();

    public void run(){
        GymUser.setCheckedIn(false);
        System.out.println(GymUser.getCheckedIn());

        timer.cancel();
    }
