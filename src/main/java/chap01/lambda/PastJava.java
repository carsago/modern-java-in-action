package chap01.lambda;

import java.util.ArrayList;
import java.util.List;

import static chap01.lambda.Color.GREEN;

public class PastJava {

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (apple.getWeight() >= 150) {
                result.add(apple);
            }
        }
        return result;
    }
}
