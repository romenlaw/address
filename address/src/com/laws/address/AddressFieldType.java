/* Generated by Together */

package com.laws.address;

import java.io.Serializable;


/**
 * AddressFieldType represents all the applicable address fields in a country (locale). Examples for address field types are:
 * <br>
 * - street number<br>
 * - street name<br>
 * - street type (st, rd, blvd ...)<br>
 * - suburb/district <br>
 * - state/province/barangay... <br>
 * Because each country has its own standards for address fields, it is generalised here to provide the right address fields suitable for the context. 
 */
public class AddressFieldType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7115078606631021557L;
	/**
	 * empty constructor
	 *
	 */
    public AddressFieldType(){}
    /**
     * constructor
     * @param name the name field
     */
    public AddressFieldType(String name) {
        this.name=name;
    }
    /**
     * constructor with all field values
     * @param localeCountry the localeCountry field
     * @param name the name field
     * @param defaultValue the defaultValue field
     * @param order the order field
     * @param suffix the suffix field
     */
	public AddressFieldType(String localeCountry, String name,
        String defaultValue, float order, String suffix) {
        setLocaleCountry(localeCountry);
        setName(name);
        setDefaultValue(defaultValue);
        setHierarchyOrder(order);
        setSuffix(suffix);
    }

	/**
	 * this method should not be used by the applications. It is here
	 * because SQLMap requires the setter to be accessible
	 * @param addressFieldTypeID
	 */
    public void setAddressFieldTypeID(Integer addressFieldTypeID){ this.addressFieldTypeID = addressFieldTypeID; }
	/**
	 * getter for addressFieldTypeID 
	 * @return addressFieldTypeID field
	 */
    public Integer getAddressFieldTypeID(){ return addressFieldTypeID; }

    /**
     * setter for localeCountry
     * @param localeCountry
     */
    public void setLocaleCountry(String localeCountry){ this.localeCountry = localeCountry; }
    /**
     * getter for localeCountry
     * @return
     */
    public String getLocaleCountry(){ return localeCountry; }

    /**
     * setter for name field
     * @param name
     */
    public void setName(String name){ this.name = name; }

    /**
     * getter for name field
     * @return
     */
    public String getName(){ return name; }

    /**
     * settor for defaultValue field
     * @param defaultValue
     */
    public void setDefaultValue(String defaultValue){ this.defaultValue = defaultValue; }

    /**
     * getter for defaultValue field
     * @return
     */
    public String getDefaultValue(){ return defaultValue; }

    /**
     * Apply the validation rules defined for this address field type. e.g. postcodes in Australia are 4-digit numbers.<br>
     * Validation rules should include:<br>
     * - mandatory/optional<br>
     * - digits/characters<br>
     * - min/max length<br>
     * - fixed value list<br>
     */
    public boolean isValid(String value) {
        boolean result=(this.name!=null);
        return result;
    }

    /**
     * setter for hierarchyOrder field
     * @param order
     */
    public void setHierarchyOrder(float order){ this.hierarchyOrder = order; }

    /**
     * getter for hierarchyOrder field
     * @return
     */
    public float getHierarchyOrder(){ return hierarchyOrder; }

    /**
     * setter for displayOrder field
     * @param order
     */
    public void setDisplayOrder(float order){ this.displayOrder = order; }

    /**
     * getter for displayOrder field
     * @return
     */
    public float getDisplayOrder(){ return displayOrder; }

    /**
     * setter for suffix field
     * @param suffix
     */
    public void setSuffix(String suffix){ this.suffix = suffix; }

    /**
     * getter for suffix field
     * @return
     */
    public String getSuffix(){ return suffix; }

    /**
     * test whether to two AddressFieldType objects have the same
     * addressFieldTypeID
     * @param other the other object to be compared with
     * @return <code>true</code> same addressFieldTypeID
     * 	<code>false</code>different or <code>null</code> addressFieldTypeID
     */
    public boolean equals(Object other) {
		if(!(other instanceof AddressFieldType))
            return false;
        AddressFieldType aft=(AddressFieldType)other;
        boolean result=false;
        if(this.addressFieldTypeID==null || aft.getAddressFieldTypeID()==null)
        	return false;
        
        if (this.addressFieldTypeID.equals(aft.getAddressFieldTypeID())
            /*
            && this.localeCountry.equals(aft.getLocaleCountry())
            && this.name.equals(aft.getName())
            && this.hierarchyOrder==aft.getDisplayOrder()
            */
            ) {
            result=true;
        }
        return result;
    }

    /**
     * string representation of this AddressFieldType
     */
    public String toString() {
        StringBuffer sb=new StringBuffer("AddressFieldType:");
        sb.append("\nid="+addressFieldTypeID);
        sb.append("\nname="+name);
        sb.append("\ncountry="+localeCountry);
        sb.append("\ndefault value="+defaultValue);
        sb.append("\nhierarchy order="+hierarchyOrder);
        sb.append("\ndisplay order="+displayOrder);
        sb.append("\nsuffix="+suffix);
        return sb.toString();
    }
    /**
     * unique ID for the AddressFeildType. This should be generated by
     * the O/R mapping layer or the database layer.
     */
    private Integer addressFieldTypeID;
    /**
     * The country where this address field type is applicable to.
     */
    private String localeCountry="Default";
    /**
     * The displayed name of the Address Field. e.g. Street Name
     */
    private String name;
    /**
     * Default value of the address field. e.g. if the address field type is Country, the default value can be set to Australia
     */
    private String defaultValue=null;
    /**
     * defines the order of the address fields. 
     * For example, a normal address should appear in the following order<br>
     * Street Number, Street Name, Suburb, State, Postcode, Country<br>
     * float is used so that we can put multiple fields into 
     * the same line but still maintaining an order using 
     * the decimal part
     */
    private float displayOrder;
    /**
     * defines the order of the address fields based on geographical
     * location logics. For example, the short form or Australian
     * addresses are represented in the following hierarchy:<p>
     * country, state, postcode, suburb, street name, street number<p>
     * ordered from large to more specific in terms of location.
     */
    private float hierarchyOrder;
    /**
     * suffix for the AddressFieldType. Not currently used.
     */
    private String suffix;
}