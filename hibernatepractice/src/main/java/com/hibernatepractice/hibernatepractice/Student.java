package com.hibernatepractice.hibernatepractice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// this is how it is used to make the table name different with the student
//@Table(name="student_table")
@Entity
public class Student {
	@Id // this is used to make the id as primary key, so hibernate come to know that
	private int id; // the annotation @Id is used to make the id attribute primary key
	
	// we can also change the name of the particular column
	//@Column(name="First_Name  ")
	private String name, studentClass;
	
	private Marks student_marks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", studentClass=" + studentClass + "]";
	}
	public Marks getStudent_marks() {
		return student_marks;
	}
	public void setStudent_marks(Marks student_marks) {
		this.student_marks = student_marks;
	}
	
	
}
