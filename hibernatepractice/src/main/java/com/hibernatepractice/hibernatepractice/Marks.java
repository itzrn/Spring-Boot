package com.hibernatepractice.hibernatepractice;

import jakarta.persistence.Embeddable;

@Embeddable
public class Marks {
	private double emgMarks;
	private double cmpMarks;
	private double mathMarks;
	public double getEmgMarks() {
		return emgMarks;
	}
	public void setEmgMarks(double emgMarks) {
		this.emgMarks = emgMarks;
	}
	public double getCmpMarks() {
		return cmpMarks;
	}
	public void setCmpMarks(double cmpMarks) {
		this.cmpMarks = cmpMarks;
	}
	public double getMathMarks() {
		return mathMarks;
	}
	public void setMathMarks(double mathMarks) {
		this.mathMarks = mathMarks;
	}
	@Override
	public String toString() {
		return "Marks [emgMarks=" + emgMarks + ", cmpMarks=" + cmpMarks + ", mathMarks=" + mathMarks + "]";
	}
	public Marks(double emgMarks, double cmpMarks, double mathMarks) {
		super();
		this.emgMarks = emgMarks;
		this.cmpMarks = cmpMarks;
		this.mathMarks = mathMarks;
	}
	public Marks() {
    }
	
}
