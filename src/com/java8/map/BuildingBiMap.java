package com.java8.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuildingBiMap {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();

        try(
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        MergingMap.class
                                                .getResourceAsStream("people.txt")
                                )
                        );
                Stream<String> stream = reader.lines();
        ){
            stream.map(line ->{
                        String[] s = line.split(" ");
                        Person p = new Person(s[0].trim(), Integer.parseInt(s[1]), s[2].trim());
                        personList.add(p);
                        return p;
                    })
                    .forEach(System.out::println);

        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        personList.forEach(System.out::println);

        Map<Integer,List<Person>> map =
                personList.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getAge
                                )
                        );
        map.forEach((age,list) -> System.out.println(age+" ->"+list));

        Map<Integer,Map<String,List<Person>>> bimap =
                new HashMap<>();

        personList.forEach(
                person ->
                        bimap.computeIfAbsent(
                                person.getAge(),
                                HashMap::new
                        ).merge(
                                person.getGender(),
                                new ArrayList<>(Arrays.asList(person)),
                                (l1,l2)->{
                                    l1.addAll(l2);
                                    return l1;
                                }
                        )
        );
        bimap.forEach(
                (age,m) -> System.out.println(age+" "+m)
        );
    }
}
