package com.infobird.entity;

public class UserEntity {

	private String name;
	private int age;
	private String phoneNo;
	private String address;
	private String city;
	
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", age=" + age + ", phoneNo="
				+ phoneNo + ", address=" + address + ", city=" + city + "]";
	}
	public UserEntity(String name, int age, String phoneNo, String address,
			String city) {
		super();
		this.name = name;
		this.age = age;
		this.phoneNo = phoneNo;
		this.address = address;
		this.city = city;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
