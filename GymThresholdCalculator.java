
public class GymThresholdCalculator extends GymUser
{
	public int maxUser = 100;
	public int numberOfCheckedIn = 0; 
 public void run() {
	 GymUser.setCheckedIn(true);
	 System.out.println(GymUser.getCheckedIn());
	    GymUser.setNumberOfCheckedIn(GymUser.getNumberOfCheckedIn()+ 1);
	    numberOfCheckedIn = numberOfCheckedIn +1;
	    
	   
	 /*   
	    if(numberOfCheckedIn >= maxUser * 60 / 100)
	    	
	    {
	    	System.out.println("the population reached 60% capacity ");
	    }
	    
	   */
	   
 }
}
