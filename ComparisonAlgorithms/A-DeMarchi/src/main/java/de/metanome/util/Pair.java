//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.util;

public class Pair<F, S> {
	public F first;
	public S second;

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public F getFirst() {
		return this.first;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public S getSecond() {
		return this.second;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			Pair<?, ?> pair = (Pair)o;
			if (this.first != null) {
				if (this.first.equals(pair.first)) {
					return this.second != null ? this.second.equals(pair.second) : pair.second == null;
				}
			} else if (pair.first == null) {
				return this.second != null ? this.second.equals(pair.second) : pair.second == null;
			}

			return false;
		} else {
			return false;
		}
	}

	public int hashCode() {
		int result = this.first != null ? this.first.hashCode() : 0;
		result = 31 * result + (this.second != null ? this.second.hashCode() : 0);
		return result;
	}

	public String toString() {
		return "(" + this.first + ", " + this.second + ")";
	}
}
