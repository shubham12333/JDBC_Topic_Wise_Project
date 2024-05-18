package PACK1;

public class ClassD 
{
	public static void main(String[] args) 
	{
		ClassC cobj = new ClassC();
		cobj.setEmpname("Shubham");
		cobj.setEmpid(101);
		cobj.setEmpdeg("Java-Developer");
		
		System.out.println("Emp Name  :: "+cobj.getEmpname());
		System.out.println("Emp ID    :: "+cobj.getEmpid());
		System.out.println("Emp Deg.  ::"+cobj.getEmpdeg());
	}
}
