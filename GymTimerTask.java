package app;
import java.util.Timer;
import java.util.TimerTask;

public class GymTimerTask extends TimerTask{

     Timer timer = new Timer();

    public void run(){
        GymUser.setCheckedIn(false);
        GymUser.setCheckedOut(true);
        GymUser.setNumberOfCheckedIn(GymUser.getNumberOfCheckedIn()- 1);
        GymUser.setNumberOfCheckedOut(GymUser.getNumberOfCheckedOut()+1);
        timer.cancel();
    }
}
