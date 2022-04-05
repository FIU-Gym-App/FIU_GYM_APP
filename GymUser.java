package app;


public class GymUser {
    private boolean checkedIn;
    private boolean checkedOut;
    private static int numberOfCheckedIn = 0;
    private static int numberOfCheckedIn = 0;


    public GymUser(){
        checkedIn = true;
        checkedOut = false;
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
     public static void setCheckedOut(boolean checkedOut){
        GymUser.checkedOut = checkedOut;
    }
    public static boolean getCheckedOut(){
        return checkedOut;
    }

    public static int getNumberOfCheckedOut() {
        return numberOfCheckedOut;
    }

    public static void setNumberOfCheckedOut(int numberOfCheckedOut) {
        GymUser.numberOfCheckedOut = numberOfCheckedOut;
    }
}
