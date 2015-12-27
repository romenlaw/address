package com.laws.util;

import java.text.Collator;
import java.util.Comparator;
/**
 * abstract base class for all Comparator classes
 * @author lawr
 * @param <T>
 */
public abstract class BaseComparator<T> implements Comparator<T> {
	/**
	 * descending order
	 */
	public final static int DESCENDING=0;
	/**
	 * ascending order
	 */
    public final static int ASCENDING=1;

    /**
     * order field, default to ASCENDING
     */
    protected int order=ASCENDING;

    /**
     * compares two objects using the Object.equals(Object)
     * @see java.lang.Object#equals(java.lang.Object)
     */
	public boolean equals(Object o) {return o.equals(this);}

	/**
	 * getter for order field
	 * @return order
	 */
    public int getOrder(){
            return order;
        }

    /**
     * setter for order field, default to ASCENDING
     * @param order
     */
    public void setOrder(int order){
        if(order==DESCENDING)
            this.order = order;
        else
            this.order = ASCENDING;
    }

    /**
     * compare two String objects
     * @param s1
     * @param s2
     * @return 0=same, 1: s1>s2, -1: s1<s2 if order is ASCENDING; reversed if order is DESCENDING
     */
    protected int compareString(String s1, String s2) {
    	int result;
        if(s1==null && s2==null)
            result = 0;
        else if(s1==null && s2!=null)
            result = -1;
        else if(s1!=null && s2==null)
            result = 1;
        else {
        	// want to support localised ordering, so using Collator instead of String
			result = Collator.getInstance().compare(s1, s2);
        }
        if(order==ASCENDING)
        	return result;
        else
        	return -result;
    }
}
