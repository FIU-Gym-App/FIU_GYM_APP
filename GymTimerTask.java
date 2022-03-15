package app;
import java.util.Timer;
import java.util.TimerTask;

public class GymTimerTask extends TimerTask{

    GymUser user = new GymUser();

    Timer timer = new Timer();

    public void run(){
        user.setCheckedIn(false);
        System.out.println(user.getCheckedIn());
        timer.cancel();
    }







}
