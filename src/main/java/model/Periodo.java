package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo implements Comparable<Periodo> {
	private Integer año;

	public Periodo(Integer año) {
		this.año = año;
	}

	public Integer getAño() {
		return año;
	}

	public void setAño(Integer año) {
		this.año = año;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((año == null) ? 0 : año.hashCode());
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
		Periodo other = (Periodo) obj;
		if (año == null) {
			if (other.año != null)
				return false;
		} else if (!año.equals(other.año))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return año.toString();
	}

	@Override
	public int compareTo(Periodo o) {
		return this.año > o.año? 1 : this.año < o.año? -1 : 0;
	}

}
