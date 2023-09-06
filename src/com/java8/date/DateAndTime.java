package com.java8.date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DateAndTime {

    public static void main(String[] args) {
        List<Person> listOfPerson = new ArrayList<>();

        try(BufferedReader reader =
                   new BufferedReader(
                           new InputStreamReader(
                                   DateAndTime.class
                                           .getResourceAsStream("people.txt")
                           )
                   );
            Stream<String> stream = reader.lines();
                ){
                stream.map(
                        line ->{
                   String[] s = line.split(" ");
                   String name = s[0].trim();
                   int year = Integer.parseInt(s[1]);
                            Month month = Month.of(Integer.parseInt(s[2]));
                            int day = Integer.parseInt(s[3]);
                            Person p = new Person(name, LocalDate.of(year,month,day));
                            listOfPerson.add(p);
                            return p;
                }).forEach(System.out::println);

        }catch (IOException IOE){
            System.out.println(" Exception is "+IOE);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        LocalDate now = LocalDate.of(2023,Month.SEPTEMBER,6);
        listOfPerson.stream()
                .forEach(
                        p ->{
                            Period period = Period.between(p.getDateOfBirth(),now);
                            System.out.println(p.getName()+" was born "
                                    +period.get(ChronoUnit.YEARS)+ " years and " +
                                    period.get(ChronoUnit.MONTHS)+" months "
                            +" ["+p.getDateOfBirth().until(now,ChronoUnit.YEARS)+" Years ]");
                        }
                );
    }

}
