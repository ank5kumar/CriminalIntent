package com.example.criminalintent;


import java.util.Date;
import java.util.UUID;

public class Employee {
	
	private UUID mId;
	private Date mDate;
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		mId = UUID.randomUUID();
		mDate = new Date();
		
	}
	@Override
	public String toString() {
		return "Employee [mId=" + mId.toString() + ", mDate=" + mDate.toString() + ", name=" + name
				+ ", age=" + age + "]";
	}
	
	
	
}
