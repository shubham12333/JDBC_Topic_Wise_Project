package PACK1;

public class ClassF extends ClassE
{
	public void meth1()
	{
		System.out.println("123");
	}
	public void meth2()
	{
		System.out.println("456");
	}
	public void meth3()
	{
		System.out.println("789");
	}
	public static void main(String[] args) 
	{
		ClassF fobj = new ClassF();
		fobj.meth1();
		fobj.meth2();
		fobj.meth3();
	}
}
