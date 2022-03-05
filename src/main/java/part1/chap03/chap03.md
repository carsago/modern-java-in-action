# chap 3

## Lambda를 통해 동작 파라미터를 활용해보자!


다음 예는 파일을 열고 한 줄을 읽는 메소드다.


```java
public static String processFileV1() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            return br.readLine();
        } catch (IOException e) {
            throw e;
        }
    }
```

짜증나는 try - catch 문이 존재하지만 별 로직은 없다.

파일을 열고 그저 한줄을 읽는다.

그렇지만 2줄을 읽고 싶다면? 혹은 3줄을 .. 아니면 텍스트에서 무언가 다른 것을 추출하고 싶다면 어떻게 해야할까?

chap02와 마찬가지로 동작 파라미터를 활용하면 간단하다.

```java
@FunctionalInterface
public interface BufferedReaderProcess {
    String process(BufferedReader br) throws IOException;
}
```

- @FunctionInterface는 단 하나의 추상메소드를 가져야만 사용가능하다.


```java
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
}
```

이제 process라는 로직을 통해 파일을 읽고 이에 적합한 값을 return 한다.

원하는 바에 따라 이를 작성하기만 하면 된다.


