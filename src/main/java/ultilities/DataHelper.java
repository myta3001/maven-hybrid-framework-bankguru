package ultilities;

import java.time.Year;
import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);
	
	public static DataHelper getData() {
		return new DataHelper();
	}
	
	public String getFullName() {
		return faker.name().fullName().replaceAll("[-+.^:,]","");
	}
	
	public String getGender() {
		if(faker.random().nextBoolean()) {
			return "male";
		} else return "female";
	}
	
	public String getDay() {
		return String.valueOf(faker.number().numberBetween(01, 30)+100).substring(1);
	}
	
	public String getMonth() {
		return String.valueOf(faker.number().numberBetween(01, 12)+100).substring(1);
	}
	
	public String getYear() {
		return Integer.toString(faker.number().numberBetween(Year.now().getValue()-80, Year.now().getValue()));
	}
	
	public String getAddress() {
		return faker.address().countryCode() + " " + faker.address().cityName() + " " + faker.address().country();
	}
	
	public String getCity() {
		return faker.address().city();
	}
	
	public String getState() {
		return faker.address().state();
	}
	
	public String getPIN() {
		return Integer.toString(faker.number().numberBetween(100000, 999999));
	}
	
	public String getMobile() {
//		return faker.phoneNumber()..replaceAll("[(-)]","");
		return faker.numerify("##########");
	}
	
	public String getEmail() {
		return faker.internet().emailAddress();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}
}
