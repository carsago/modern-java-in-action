package part1.chap02.behavparam.predicate;

import part1.chap02.behavparam.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() >= 150;
    }
}
