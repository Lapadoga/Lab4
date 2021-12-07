package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class designed to record data about foreign people from a csv file.
 */
public class CSV {
    /**
     * An array that stores information about all the people in the file
     */
    public ArrayList<Human> humans;
    /**
     * An array that stores all the divisions from the file
     */
    public ArrayList<Division> divisions;
    /**
     * The directory to the csv file
     */
    public String path;

    /**
     * Class Constructor
     * @param directory Directory of csv file
     */
    public CSV(String directory) {
        this.path = directory;
        humans = new ArrayList<>();
        divisions = new ArrayList<>();
    }

    /**
     * The main method. Reads a file and writes data to an array and a set if necessary.
     * @throws Exception If it's not a file or it has a non-csv extension
     */
    public void reading() throws Exception {
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(path));
        }
        catch (Exception ex) {
            throw new Exception("This directory was not found or the file does not exist in the directory!");
        }
        String[] dirSplit = path.split("/");// File csv
        if (!dirSplit[dirSplit.length-1].split("\\.")[1].equals("csv")) {
            throw new Exception("Reading is possible only for csv files!");
        }
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(";");
            if (row.length != 6) {
                throw new Exception("Some values are missing!");
            }
            checkID(row[0]);
            checkGender(row[2]);
            checkBirthdate(row[3]);
            checkDivision(row[4]);
            checkSalary(row[5]);
            Division div = new Division(row[4].charAt(0));
            if (!divisions.contains(div)) {
                divisions.add(div);
            }
            humans.add(new Human(Integer.parseInt(row[0]), row[1], row[2], div, Integer.parseInt(row[5]), row[3]));
        }
    }

    /**
     * Checking the ID from the file
     * @param id Current ID
     * @throws Exception If extra characters are allowed in the ID column
     */
    private void checkID(String id) throws Exception {
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                throw new Exception("ID contains invalid characters.");
            }
        }
    }

    /**
     * Checking the gender from the file
     * @param gender Current gender
     * @throws Exception If the gender does not match
     */
    private void checkGender(String gender) throws Exception {
        if (!gender.equals("Male") && !gender.equals("Female")) {
            throw new Exception("The gender is incorrect!");
        }
    }

    /**
     * Checking the birthdate from the file
     * @param birthdate Current birthdate
     * @throws Exception If the date is incorrect
     */
    private void checkBirthdate(String birthdate) throws Exception {
        for (int i = 0; i < birthdate.length(); i++) {
            if ((birthdate.charAt(i) < '0' || birthdate.charAt(i) > '9') && birthdate.charAt(i) != '.') {
                throw new Exception("Date contains invalid characters.");
            }
        }
        int[] daysInMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] birth = birthdate.split("\\."); //07 08 1988
        if (birth.length != 3) {
            throw new Exception("Incorrect date of birth");
        }
        if (birth[0].length() != 2 || birth[1].length() != 2) {
            throw new Exception("Incorrect length of the day or month");
        }
        if (Integer.parseInt(birth[2]) % 4 == 0) {
            if (birth[1].equals("02")) {
                if (Integer.parseInt(birth[0]) > 29) {
                    throw new Exception("The day exceeds the allowed value.");
                }
            }
            else if (daysInMonth[Integer.parseInt(birth[1]) - 1] < Integer.parseInt(birth[0])){
                throw new Exception("The day exceeds the allowed value.");
            }
        } else if (daysInMonth[Integer.parseInt(birth[1]) - 1] < Integer.parseInt(birth[0])) {
            throw new Exception("The day exceeds the allowed value.");
        }
    }

    /**
     * Checking the division from the file
     * @param division Current division
     * @throws Exception If the division consists of more than one character
     */
    private void checkDivision(String division) throws Exception {
        if (division.length() > 1) {
            throw new Exception("Incorrect division is indicated. It must contain one letter or number.");
        }
    }

    /**
     * Checking the salary from the file
     * @param salary Current salary
     * @throws Exception If there are extra characters in the salary
     */
    private void checkSalary(String salary) throws Exception {
        for (int i = 0; i < salary.length(); i++) {
            if (salary.charAt(i) < '0' || salary.charAt(i) > '9') {
                throw new Exception("Salary contains invalid characters.");
            }
        }
    }

    /**
     * Overriding the method for comparing objects.
     * @param o Object to compare
     * @return true if equal, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSV csv = (CSV) o;
        return Objects.equals(humans, csv.humans) && Objects.equals(divisions, csv.divisions) && Objects.equals(path, csv.path);
    }

    /**
     * Overriding the hash code method
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return humans.size() - divisions.size();
    }
}
