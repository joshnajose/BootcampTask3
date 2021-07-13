package Test;
import java.util.List;
import com.fis.app.model.ElectronicDevice;
import com.fis.app.dao.ElectronicDeviceImpl;
import com.fis.app.dao.IElectronicDeviceDAO;
public class TestRunnerClass
{
public static void main(String args[])
{
	try
	{
		IElectronicDeviceDAO dao=new ElectronicDeviceImpl();
		List<ElectronicDevice> list=dao.getAllDevices();
		List<ElectronicDevice> list1=dao.getDeviceBasedOnPriceRangeAndType(15000,45000,"Camera");
		for(ElectronicDevice filtered:list1)
		{
			System.out.println(filtered);
		}
	}
	catch(Exception e)
	{
		System.out.println("Problem "+e);
	}
}
}
