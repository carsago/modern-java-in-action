package part1.chap02.behavparam;


import part1.chap02.behavparam.predicate.AppleGreenColorPredicate;
import part1.chap02.behavparam.predicate.ApplePredicate;
import part1.chap02.behavparam.predicate.AppleRedAndHeavyPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static part1.chap02.behavparam.Color.GREEN;
import static part1.chap02.behavparam.Color.RED;

public class FilterLogic {


    /**
     * filter GreenApple
     */

    public static List<Apple> filterGreenApple(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * filter specific color apple
     */

    public static List<Apple> filterAppleByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 무게를 통한 필터링에 대한 고객 요구가 추가됨
     * But, 색 필터와 너무 코드가 중복된다.
     * 또한 다른 필드에 의한 필터 요구가 추가될 수 있다.
     */

    public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() >= weight) {
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * predicate 사용하여 추상적 조건으로 필터링
     */

    public static List<Apple> filterApples(List<Apple> inventory,
                                           ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(150, GREEN),
                new Apple(180, RED),
                new Apple(120, RED)
        );
        
        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());

        /**
         * 익명 클래스를 사용해보자!
         */

        List<Apple> redAndLightApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return RED.equals(apple.getColor())
                        && apple.getWeight() <= 120;
            }
        });

        /**
         * Lambda를 통해 익명 클래스를 구현하자
         */

        List<Apple> greenAndSpecificWeightApples = filterApples(inventory,
                a -> GREEN.equals(a.getColor()) && a.getWeight() == 150);



    }



}
