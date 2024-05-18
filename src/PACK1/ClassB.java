package PACK1;

public class ClassB extends ClassA
{
	public void meth1()
	{
		System.out.println(11);
	}
	public void meth5()
	{
		System.out.println("ClassB "+55);
	}
	public static void main(String[] args) 
	{
		ClassB bobj = new ClassB();
		bobj.meth1();
		bobj.meth2();
		bobj.meth3();
		bobj.meth4();
		ClassA aobj = new ClassA();
		aobj.meth1();
		ClassA aobj1 = new ClassB();
		aobj1.meth1();
		aobj1.meth5();
	}
}
