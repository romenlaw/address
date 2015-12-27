package com.laws.address;
/**
 * Representing status of Address objects.
 * @author lawr
 */
public enum AddressStatus {
	ACTIVE(1), INACTIVE(0);
	
	private final int status;
	AddressStatus(int i) {
		this.status=i;
	}
	public int getStatus() {
		return status;
	}
}
