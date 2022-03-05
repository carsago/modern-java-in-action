package part1.chap03;

import part1.chap02.behavparam.Apple;
import part1.chap02.behavparam.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;
import static part1.chap02.behavparam.Color.GREEN;
import static part1.chap02.behavparam.Color.RED;
import static part1.chap02.behavparam.predicateV2.Predicate.filter;

public class FileReadV1 {

    public static String processFileV1() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            return br.readLine();
        } catch (IOException e) {
            throw e;
        }
    }

    public static String processFileV2(BufferedReaderProcess b) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            return b.process(br);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        String oneLine = processFileV2(br -> br.readLine());
        String twoLine = processFileV2(br -> br.readLine() + br.readLine());

        List<Apple> inventory = Arrays.asList(
                new Apple(100, RED),
                new Apple(150, RED),
                new Apple(80, GREEN),
                new Apple(120, GREEN)
        );
        inventory.sort(comparing(Apple::getWeight));
    }
}


