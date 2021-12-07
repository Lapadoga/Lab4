package com.company;

import java.util.Date;
import java.util.Objects;

/**
 * A class of a person with information about his id, name, gender,
 * birthday, work in the department and salary.
 */
public class Human {
    /**
     * ID
     */
    public int id;
    /**
     * Name
     */
    public String name;
    /**
     * Gender
     */
    public String gender;
    /**
     * Division
     */
    public Division division;
    /**
     * Salary
     */
    public long salary;
    /**
     * Date of birth
     */
    public String birthdate;

    /**
     * Initialization
     * @param id ID
     * @param name Name
     * @param gender Gender
     * @param division Division
     * @param salary Salary
     * @param birthdate Date of birth
     */
    public Human(int id, String name, String gender, Division division, long salary, String birthdate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.division = division;
        this.salary = salary;
        this.birthdate = birthdate;
    }

    /**
     * Overriding the method to return a string
     * @return A line with information about a person
     */
    @Override
    public String toString() {
        return "ID"+id+" name " + name +" gender " + gender + " birthdate " + birthdate + " salary " + salary + " in division " + division ;
    }

    /**
     * Overriding the method for comparing objects.
     * @param o Object to compare
     * @return true if equal and false else.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return id == human.id && salary == human.salary && Objects.equals(name, human.name) && Objects.equals(gender, human.gender) && Objects.equals(birthdate, human.birthdate);
    }

    /**
     * Overriding the hash code method
     * @return Hash code.
     */
    @Override
    public int hashCode() { return id * name.length() * 2 - gender.length(); }
}
