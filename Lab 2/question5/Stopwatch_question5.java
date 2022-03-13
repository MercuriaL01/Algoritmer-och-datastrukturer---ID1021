//This stopwatch was implemented in the same way as the Algorithms 4th edition book did it
public class Stopwatch_question5
{
	private long start;

	public Stopwatch_question5()
	{
		//This gets the time in milliseconds of when the stopwatch object was created
		start = System.currentTimeMillis();
	}

	public double elapsedTime()
	{
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0; //Divide with 1000 since milliseconds = "tusendelar"
	}

	public void reset()
	{
		start = System.currentTimeMillis();
	}
}
