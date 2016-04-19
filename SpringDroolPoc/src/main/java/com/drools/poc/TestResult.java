package com.drools.poc;

public class TestResult {

	@Override
	public String toString() {
		return "TestResult [id=" + id + ", description=" + description + ", valueInteger=" + valueInteger + ", testrecordId=" + testrecordId + "]";
	}
	private int id;
	private String description;
	private int valueInteger;
	private int testrecordId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getValueInteger() {
		return valueInteger;
	}
	public void setValueInteger(int valueInteger) {
		this.valueInteger = valueInteger;
	}
	public int getTestrecordId() {
		return testrecordId;
	}
	public void setTestrecordId(int testrecordId) {
		this.testrecordId = testrecordId;
	}


}
