package part1.chap02.behavparam.predicate;

import part1.chap02.behavparam.Apple;
import part1.chap02.behavparam.Color;

import static part1.chap02.behavparam.Color.GREEN;

public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }
}
