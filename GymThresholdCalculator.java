
public class GymThresholdCalculator extends GymUser
{
	public int maxUser = 500;
 
 public void thresholdCalculator(){
	
	   int currentPopulation = GymUser.getNumberOfCheckedIn();
	   int numberCheckedIn = currentPopulation/maxUser;

	   
	   
	    if(numberCheckedIn >= 0.4*(maxUser) && numberCheckedIn <= 0.6*(maxUser))
	    	
	    {
	    	System.out.println("the population reached 60% capacity ");
	    }
	    
	  
	   
 }
}
