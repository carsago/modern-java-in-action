package chap01.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static chap01.lambda.Color.*;

public class ModernJava {

    public static boolean isGreenApple(Apple apple) {
        return GREEN.equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() >= 150;
    }

    static List<Apple> filterApples(List<Apple> inventory,
                                    Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(50, GREEN));
        inventory.add(new Apple(100, GREEN));
        inventory.add(new Apple(220, GREEN));
        inventory.add(new Apple(160, RED));
        inventory.add(new Apple(140, RED));
        inventory.add(new Apple(200, RED));

        /**
         * 이와 같이 사용
         */

        List<Apple> greenApples = filterApples(inventory, ModernJava::isGreenApple);
        List<Apple> heavyApples = filterApples(inventory, ModernJava::isHeavyApple);

        for (Apple greenApple : greenApples) {
            System.out.println("  color : " + greenApple.getColor() +
                    "   weight : " + greenApple.getWeight());
        }

        /**
         * 심지어 정의를 안할 수도 있다 !
         */
        List<Apple> twoCriteriaApple = filterApples(inventory, a ->
                a.getWeight() > 200 && GREEN.equals(a.getColor()));

        for (Apple apple : twoCriteriaApple) {
            System.out.println("apple = " + apple.getWeight() +"  "  + apple.getColor());
        }


    }

}
