package com.java8.string;

import java.util.stream.IntStream;

public class StringJava8 {
    public static void main(String[] args) {
        String s = "Hello World";

        IntStream stream = s.chars();
        stream.mapToObj(letter -> (char)letter)
                .map(Character::toUpperCase)
                .forEach(System.out::print);

        System.out.println("==========");
        String s1 ="Hello";
        String s2 ="World";
        String s3 =s1+" "+s2;
        System.out.println(s3);
        System.out.println("==============");
        StringBuffer sb1 = new StringBuffer();
        sb1.append("Hello");
        sb1.append(" ").append("World");
        System.out.println(sb1);
    }
}
