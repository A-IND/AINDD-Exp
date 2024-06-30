//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.metanome.algorithms.aspider.sorting;

public class TPMMSTuple implements Comparable<TPMMSTuple> {
    public String value;
    public final int readerNumber;

    public TPMMSTuple(String value, int readerNumber) {
        this.value = value;
        this.readerNumber = readerNumber;
    }

    public int compareTo(TPMMSTuple other) {
        return this.value.compareTo(other.value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TPMMSTuple)) {
            return false;
        } else {
            TPMMSTuple other = (TPMMSTuple)obj;
            return this.value.equals(other.value);
        }
    }

    public String toString() {
        return "Tuple(" + this.value + "," + this.readerNumber + ")";
    }
}
