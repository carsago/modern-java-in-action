package part1.chap03;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class GreetingInit {

    public static void main(String[] args) {
        UnaryOperator<String> hiV1 = (s) -> "hi " + s;

        UnaryOperator<String> hiV2 = Greeting::hi;
        hiV2.apply("Junsu");

        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        hello.apply("SuZin");

        Supplier<Greeting> noArgsConstructor = Greeting::new;
        Greeting greetingWithNoArgs = noArgsConstructor.get();

        Function<String, Greeting> nameConstructor = Greeting::new;
        Greeting greetingWithName = nameConstructor.apply("this is my name");


        String[] strings = {"A","B","C","D"};

        Arrays.sort(strings, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(strings));
    }
}
