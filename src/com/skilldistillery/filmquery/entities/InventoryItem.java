package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class InventoryItem {
	private String filmTitle;
	private int invId;
	private String condition;
	private String address;
	
	
	public InventoryItem() {}

	
	public InventoryItem(String filmTitle, int invId, String condition, String address) {
		this.filmTitle = filmTitle;
		this.invId = invId;
		this.condition = condition;
		this.address = address;
	}

	public String getFilmTitle() {
		return filmTitle;
	}
	
	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
	}

	public int getInvId() {
		return invId;
	}

	public void setInvId(int invId) {
		this.invId = invId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, condition, filmTitle, invId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		return Objects.equals(address, other.address) && Objects.equals(condition, other.condition)
				&& Objects.equals(filmTitle, other.filmTitle) && invId == other.invId;
	}

	@Override
	public String toString() {
		return filmTitle + "\tItem #: "+ invId + "\t" + condition + "\tStore Location:   " + address;
	}
}