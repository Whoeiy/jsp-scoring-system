package mybean.data;

import com.sun.rowset.CachedRowSetImpl;

public class ExtraName {
	String extra = "", detail = "", name = "", max = "", min = "";
	CachedRowSetImpl rowSet = null;

	public CachedRowSetImpl getRowSet() {
		return rowSet;
	}

	public void setRowSet(CachedRowSetImpl rowSet) {
		this.rowSet = rowSet;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}
	
}
