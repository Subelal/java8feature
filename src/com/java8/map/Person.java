package com.java8.map;

public class Person {
    private String name;
    private int age;
    private String gender;

    public Person() {
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    public int getAge() {

        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Person ["+this.name+", "+this.age+", "+this.gender+"]";
    }
}
