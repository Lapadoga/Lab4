package com.company;

import java.util.Objects;

/**
 * A class of a division with information about it.
 */
public class Division {
    /**
     * ID
     */
    public int id;
    /**
     * Title of division
     */
    public char name;

    /**
     * Initialization
     * @param title Division name
     */
    public Division(char title) {
        this.name = title;
        id = (int) title;
    }

    @Override
    public String toString() {
        return "ID" + id + " name " + name;
    }

    /**
     * Overriding the method for comparing objects.
     * @param o Object to compare.
     * @return true if equal and false else.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return id == division.id && Objects.equals(name, division.name);
    }

    /**
     * Overriding the hash code method
     * @return Hash code
     */
    @Override
    public int hashCode() { return (int) name; }
}
