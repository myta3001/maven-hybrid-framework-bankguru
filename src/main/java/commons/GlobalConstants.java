package commons;

import java.io.File;

public class GlobalConstants {

	public static final long LONG_TIMEOUT = 25;
	public static final long SHORT_TIMEOUT = 5;
	
	public static final String BANK_GURU_REGISTER_ACCOUNT_LINK = "https://demo.guru99.com/";
	public static final String BANK_GURU_LOGIN_LINK = "https://demo.guru99.com/v4/";
	public static final String BANK_GURU_DASHBOARD_LINK = "https://demo.guru99.com/v4/manager/Managerhomepage.php";
	public static final String BANK_GURU_NEW_CUSTOMER_LINK = "https://demo.guru99.com/v4/manager/addcustomerpage.php";
	public static final String BANK_GURU_EDIT_CUSTOMER_LINK = "https://demo.guru99.com/v4/manager/EditCustomer.php";
	public static final String BANK_GURU_DELETE_CUSTOMER_LINK = "https://demo.guru99.com/v4/manager/DeleteCustomerInput.php";
	public static final String BANK_GURU_NEW_ACCOUNT_LINK = "https://demo.guru99.com/v4/manager/addAccount.php";
	public static final String BANK_GURU_EDIT_ACCOUNT_LINK = "https://demo.guru99.com/v4/manager/editAccount.php";
	public static final String BANK_GURU_DELETE_ACCOUNT_LINK = "https://demo.guru99.com/v4/manager/deleteAccountInput.php";

	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String OS_VERSION = System.getProperty("os.version");
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String EXTENT_REPORT = PROJECT_PATH + File.separator + "extentReportResult" + File.separator;
}
