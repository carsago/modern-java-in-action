package part1.chap03;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcess {
    String process(BufferedReader br) throws IOException;
}
