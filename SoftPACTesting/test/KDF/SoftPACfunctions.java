package KDF;

public class SoftPACfunctions {
	
	public static int penalty(String deposit, String penalty)
	{		
		float dep = Float.parseFloat(deposit);
		int deposit_amt = (int) dep;
		int pen = Integer.parseInt(penalty);
		int cal_penalty = (2*deposit_amt)/100;
		if(cal_penalty == pen)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
}
