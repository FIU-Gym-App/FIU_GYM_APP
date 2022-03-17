package app;


public class GymUser {
    private static boolean checkedIn;
    private static int numberOfCheckedIn = 0;


    public GymUser(){
        checkedIn = true;
        numberOfCheckedIn++;

    }


    public static void setCheckedIn(boolean checkedIn){
        GymUser.checkedIn = checkedIn;
    }
    public static boolean getCheckedIn(){
        return checkedIn;
    }

    public static int getNumberOfCheckedIn() {
        return numberOfCheckedIn;
    }

    public static void setNumberOfCheckedIn(int numberOfCheckedIn) {
        GymUser.numberOfCheckedIn = numberOfCheckedIn;
    }
}
