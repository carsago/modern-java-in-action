package part1.chap03;

import part1.chap02.behavparam.Apple;

import java.util.Comparator;

public class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple a1, Apple a2) {
        return ((Integer) a1.getWeight()).compareTo(a2.getWeight());
    }
}
