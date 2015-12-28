package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.laws.address.Address;
import com.laws.address.AddressField;
import com.laws.address.AddressFieldType;
import com.laws.address.AddressStatus;

@RunWith(Parameterized.class)
public class TestAddress2 {
	static String usa = "USA";
	static Address addr, addr2, addr3;

	static AddressFieldType usStreetNo;
	static AddressFieldType usStreetName;
	static AddressFieldType usSuburb;
	static AddressFieldType usCity;
	static AddressFieldType usState;
	static AddressFieldType usPostcode;
	static AddressFieldType usCountry;

	static AddressField streetNo;
	static AddressField streetName;
	static AddressField city;
	static AddressField suburb;
	static AddressField postcode;
	static AddressField country;
	
	static AddressField streetNo2, streetNo3;
	static AddressField streetName2;
	static AddressField city2;
	static AddressField suburb2;
	static AddressField postcode2;
	static AddressField country2;

	// for parameterized tests
	private Boolean equalResult, identicalResult;
	private Address a1, a2;
	

	//@BeforeClass
	public static  void init() throws Exception {
		// set up address field types
		usStreetNo = new AddressFieldType(usa, "street no", usa, 1.0f, null);
		usStreetName = new AddressFieldType(usa, "street name", null, 1.1f,
				null);
		usSuburb = new AddressFieldType(usa, "suburb", null, 2.0f, null);
		usCity = new AddressFieldType(usa, "city", null, 2.1f, null);
		usState = new AddressFieldType(usa, "state", null, 3.0f, null);
		usPostcode = new AddressFieldType(usa, "postcode", null, 3.1f, null);
		usCountry = new AddressFieldType(usa, "country", usa, 4.0f, null);

		// prepare 1st set of address fields
		streetNo = new AddressField(usStreetNo, "1a");
		streetName = new AddressField(usStreetName, "Hollywood Blvd.");
		city = new AddressField(usCity, "LA");
		suburb = new AddressField(usSuburb, "Hollywood");
		postcode = new AddressField(usPostcode, "90210");
		country = new AddressField(usCountry, "USA");

		// prepare 2nd set of address fields
		streetNo2 = new AddressField(usStreetNo, "100");
		streetName2 = new AddressField(usStreetName, "Jump Street");
		city2 = new AddressField(usCity, "Melbourne");
		suburb2 = new AddressField(usSuburb, "Rockdale");
		postcode2 = new AddressField(usPostcode, "90982");
		country2 = new AddressField();
		country2.setValue("Australia");
		country2.setAddressFieldType(usCountry);
		
		streetNo3 = new AddressField(usStreetNo, "222a");
		
		addr=new Address(AddressStatus.ACTIVE);			
		addr.setAddressField(streetNo);
		addr.setAddressField(streetName);
		addr.setAddressField(city);
		addr.setAddressField(suburb);
		addr.setAddressField(postcode);
		addr.setAddressField(country);
		
		country.setAddressFieldID(null);
		postcode.setAddressFieldID(null);
		addr3=new Address(AddressStatus.ACTIVE);
		addr3.setAddressField(country);
		addr3.setAddressField(postcode);
		addr3.setAddressField(suburb);
		addr3.setAddressField(city);			
		addr3.setAddressField(streetNo);
		addr3.setAddressField(streetName);
		
		addr2=new Address();
		addr2.setStatus(AddressStatus.valueOf("INACTIVE"));
		List<AddressField> list=new ArrayList<AddressField>();
		list.add(streetNo2);
		list.add(country2);
		list.add(city2);
		addr2.setAddressFields(list);
		
		System.out.println(addr);
		System.out.println(addr2);
		System.out.println(addr3);
	}

	public TestAddress2(Boolean eqResult, Boolean idResult, Address a1, Address a2) {
		this.equalResult=eqResult;
		this.identicalResult=idResult;
		this.a1=a1;
		this.a2=a2;
	}
	@Parameters
	public static Collection<Object[]> parameters() {
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// equalResult, identicalResult, address 1, address 2
		return Arrays.asList(new Object[][] {
			{ false, true, addr, addr3 },
			{ false, false, addr, addr2 },
			{ false, true, addr3, addr },
			{ false, false, addr2, addr3 },
			{ false, false, addr3, addr2 },
		});
	}
	@Test
	public void testEquals() {
		assertEquals(equalResult, a1.equals(a2));
	}
	@Test
	public void testIdentical() {
		assertEquals(identicalResult, a1.identical(a2));
	}
}
