package com.datadrizzle.ui.model;

public class Pair<Name, Value> {
	Name name;
	Value value;
	
	public Pair(Name name, Value value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Pair [name=" + name + ", value=" + value + "]";
	}
}
