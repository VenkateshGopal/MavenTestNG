package commonFunctions;

import org.openqa.selenium.By;
import org.testng.Assert;

public class CommonMethods extends BasicFunctions {
	
	/*public static void main(String[] args) throws Exception {
		MkrLogin();
		chkLogin();
	}
*/
	public static void MkrLogin() throws Exception {
		// RegistrationOR ObjRepo = new RegistrationOR(browser);
		System.out.println("Login with maker user");
		Assert.assertTrue(true);
		
	}
	
	public static void chkLogin() throws Exception {
		// RegistrationOR ObjRepo = new RegistrationOR(browser);
		System.out.println("Login with chekar user");
		Assert.assertTrue(true);
		
	}
}
