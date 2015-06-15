package io.rxjava.domain;


/**
 * Created by gmind on 2015-04-15.
 */
public class IPhong {

	private int id;

	private String prodcut;

	public IPhong(int id, String prodcut) {
		this.id = id;
		this.prodcut = prodcut;
	}

	public IPhong(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProdcut() {
		return prodcut;
	}

	public void setProdcut(String prodcut) {
		this.prodcut = prodcut;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IPhong iPhong = (IPhong)o;

		if (id != iPhong.id) {
			return false;
		}
		if (prodcut != null ? !prodcut.equals(iPhong.prodcut) : iPhong.prodcut != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (prodcut != null ? prodcut.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "IPhong{" +
			"id=" + id +
			", prodcut='" + prodcut + '\'' +
			'}';
	}
}
