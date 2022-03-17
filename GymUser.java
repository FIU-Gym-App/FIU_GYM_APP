package app;


public class GymUser {
    private boolean checkedIn;
    private int numberOfCheckedIn;
    

    public GymUser(){
        checkedIn = true;
        
    }


    public void setCheckedIn(boolean checkedIn){
        this.checkedIn = checkedIn;
    }
    public boolean getCheckedIn(){
        return checkedIn;
    }

    public int getNumberOfCheckedIn() {
        return numberOfCheckedIn;
    }

    public void setNumberOfCheckedIn(int numberOfCheckedIn) {
        this.numberOfCheckedIn = numberOfCheckedIn;
    }
}
