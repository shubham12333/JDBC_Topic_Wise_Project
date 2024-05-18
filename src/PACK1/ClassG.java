package PACK1;

public class ClassG extends Thread
{
	public void run()
	{
		for(int i=0;i<=5;i++)
		{
			System.out.println("run() executed: "+i);
		}
	}
	public static void main(String[] args) 
	{
		ClassG gobj = new ClassG();
		Thread t1 = new Thread(gobj);
		t1.start();
		
		Thread t2 = new Thread(gobj);
		t2.start();
		t1.run();
	}
}
