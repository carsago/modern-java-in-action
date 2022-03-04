package part1.chap02.behavparam.predicateV2;

import part1.chap02.behavparam.Apple;
import part1.chap02.behavparam.Color;

import java.util.ArrayList;
import java.util.List;

import static part1.chap02.behavparam.Color.RED;

public interface Predicate<T> {
    boolean test(T t);

    /**
     * 바나나, 딸기 뭘로든 할 수 있다!!!
     */

    public static <T> List<T> filter(List<T> inventory, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : inventory) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(80, RED));
        filter(inventory, a -> RED.equals(a.getColor()));
    }
}
