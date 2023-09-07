package com.java8.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergingMap {
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
        System.out.println("from list");
        personList.forEach(System.out::println);
        List<Person> list1 = personList.subList(1,5);
        List<Person> list2 = personList.subList(5, personList.size());

        System.out.println("Map1");
        Map<Integer,List<Person>> map1 = mapByAge(list1);
        map1.forEach((age,list)-> System.out.println(age+" -> "+list));
        System.out.println("Map2");
        Map<Integer,List<Person>> map2 = mapByAge(list2);
        map2.forEach((age,list)-> System.out.println(age+" -> "+list));

        map2.entrySet().stream()
                .forEach(
                        entry ->
                                map1.merge(
                                        entry.getKey(),
                                        entry.getValue(),
                                        (l1,l2) ->{
                                            l1.addAll(l2);
                                            return l1;
                                        }
                                )
                );
        System.out.println("Map1 merge");
     //   Map<Integer,List<Person>> map1 = mapByAge(list1);
        map1.forEach((age,list)-> System.out.println(age+" -> "+list));
    }
    private static Map<Integer,List<Person>> mapByAge(List<Person> list){
        Map<Integer,List<Person>> map =
                list.stream().collect(
                        Collectors.groupingBy(
                                Person::getAge
                        )
                );
        return map;

    }
}
