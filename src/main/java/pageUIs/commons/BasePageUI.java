package pageUIs.commons;

public class BasePageUI {

	public static final String UPLOAD_FILE = "xpath = //input[@type='file']";

	public static final String ELEMENT_BY_NAME = "name = %s";
	public static final String ELEMENT_BY_VALUE = "css = *[value='%s']";
	public static final String NEXT_CELL_BY_TEXT = "xpath = //td[text()='%s']/following-sibling::td";

	public static final String ELEMENT_VALIDATE_MESSAGE_BY_NAME = "xpath= //*[@name='%s']/following-sibling::label";
	
	public static final String DYNAMIC_LEFT_MENU_BY_TEXT = "xpath = //a[text()='%s']";
	
	public static final String ADS_IFRAME = "xpath = //iframe[contains(@id,'google_ads_iframe')]";
	public static final String ADS_SUB_IFRAME = "xpath= //iframe[contains(@id,'ad_iframe')]";
	public static final String CLOSE_ADS_SUB_IFRAME_BUTTON = "xpath = //div[@id='dismiss-button']";
	public static final String CLOSE_ADS_IFRAME_BUTTON = "xpath = //div[@class='toprow']/div[@id='dismiss-button']";

}
