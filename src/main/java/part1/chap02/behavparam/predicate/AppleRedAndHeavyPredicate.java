package part1.chap02.behavparam.predicate;

import part1.chap02.behavparam.Apple;
import part1.chap02.behavparam.Color;

import static part1.chap02.behavparam.Color.*;

public class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return RED.equals(apple.getColor())
                && apple.getWeight() >= 150;
    }
}
