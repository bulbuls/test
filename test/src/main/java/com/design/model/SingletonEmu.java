package com.design.model;

public enum SingletonEmu {
	MON("1"), TUE("2"), WED("3"), THU("4"), FRI("5"), SAT("6"), SUN("7");
	SingletonEmu(String a){
		this.a = a;
	}
	private String a;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	
}
