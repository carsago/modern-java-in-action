package part1.chap03;

import part1.chap02.behavparam.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;
import static part1.chap02.behavparam.Color.GREEN;
import static part1.chap02.behavparam.Color.RED;

public class Practice {


    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(150, GREEN),
                new Apple(180, RED),
                new Apple(120, RED)
        );

        //1단계
        inventory.sort(new AppleComparator());


        //2 ~ 3단계
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return ((Integer) a1.getWeight()).compareTo(a2.getWeight());
            }
        });

        inventory.sort((a1,a2) -> ((Integer) a1.getWeight()).compareTo(a2.getWeight()));


        Comparator<Apple> c = comparing(a -> a.getWeight());

        inventory.sort(comparing(a -> (Integer) a.getWeight()));

        //4단계
        inventory.sort(comparing(Apple::getWeight));
    }
}
