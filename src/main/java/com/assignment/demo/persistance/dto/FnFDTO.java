/**
 * 
 */
package com.assignment.demo.persistance.dto;

/**
 * @author gsiva
 *
 */
public class FnFDTO {

	private String location;
	private String name;
	private char category;

	public FnFDTO() {

	}

	public FnFDTO(String name, char category) {
		this(null, name, category);
	}

	public FnFDTO(String location, String name, char category) {
		this.location = location;
		this.name = name;
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getCategory() {
		return category;
	}

	public void setCategory(char category) {
		this.category = category;
	}

}
