package com.datadrizzle.entities;

import java.util.List;

public class Chart<XType, YType> {

	/* fields needed to draw bar chart on UI
	 * x: ['giraffes', 'orangutans', 'monkeys', 'lion'], y: [20, 14, 23, 10],
	 * name: 'SF Zoo', type: 'bar'
	 */

	private String type;
	private String name;
	private List<XType> x;
	private List<YType> y;

	public Chart() {

	}

	public Chart(String type, String name, List<XType> x, List<YType> y) {
		super();
		this.type = type;
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<XType> getX() {
		return x;
	}

	public void setX(List<XType> x) {
		this.x = x;
	}

	public List<YType> getY() {
		return y;
	}

	public void setY(List<YType> y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chart other = (Chart) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Chart [type=" + type + ", name=" + name + ", x=" + x + ", y=" + y + "]";
	}
}
