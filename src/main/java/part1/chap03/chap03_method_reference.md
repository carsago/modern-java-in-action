# 메소드 참조를 알아보자


```java
inventory.sort(comparing(a ->  a.getWeight()));

inventory.sort(comparing(Apple::getWeight));
```

chap03.md에서 나왔던 메소드 참조 활용이다.

뭔가 알거같으면서도 모르겠는 느낌이다.

다음 코드는 백기선님의 "더 자바 - 자바 8" 강의를 바탕으로 이루어졌다.
- https://www.inflearn.com/course/the-java-java8/
```java
public class Greeting {

    private String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    public String hello(String name) {
        return "hello " + name;
    }

    public static String hi(String name) {
        return "hi " + name;
    }
}
```
###스태틱 메소드 참조

만약 우리가 string을 밭아서 hi에 붙이는 함수를 만들고 싶다면 다음과 같이 할 수 있다.

```java
UnaryOperator<String> hiV1 = (s) -> "hi " + s;
```

그런데 이는 Greeting.hi(s);와 똑같은 역할을 한다.

여기선 이해를 위해 조금 억지 예제를 들었지만 실제로 내가 굳이 구현하지 않아도
이미 내가 원하는 많은 기능들이 메소드로 존재할 것이다.

이럴때 굳이 람다를 통해 구현하지 않고, 기존에 존재하는 메소드를 가져다가 쓰는 것이 
메소드 참조이다.

메소드 참조를 통해 다음과 "hi " + name을 출력할 수 있다.

```java
UnaryOperator<String> hiV2 = Greeting::hi;
```

이를 통해 기존의 메소드를 재활용했고, 메소드의 이름을 통해 조금 더 코드가
이해하기 쉽게 전달될 수 있다(특히, 더욱 복잡한 코드일 경우).

이를 일반화 하면 다음과 같이 정의할 수 있다.

```java
//lambda 
(args) -> ClassName.staticMethod(args)

//Method Reference
ClassName::staticMethod

//예제의 활용 예 보자, 파라미터가 apply에 들어간다.
UnaryOperator<String> hi = Greeting::hi;
hi.apply("Junsu");

```


### 인스턴스 메소드 참조

다만 위 코드는 스태틱 메소드에 대한 메소드 참조였으므로
클래스에 대한 구현체가 필요없었다.

만약 인스턴스 메소드에 대한 참조가 필요하다면 Greeting의 구현체가 필요하다.

```java
Greeting greeting = new Greeting();
UnaryOperator<String> hello = greeting::hello;
```

일반화한 예시
```java
//lambda
(args) -> Instance.instanceMethod(args)

//Method reference
Instance::instanceMethod

//예제
Greeting greeting = new Greeting();
UnaryOperator<String> hello = greeting::hello;
hello.apply("SuZin");
```

### 생성자 참조
메소드 참조는 생성자에 대해서도 가능하다.

```java
Supplier<Greeting> noArgsConstructor = Greeting::new;
Greeting greetingWithNoArgs = noArgsConstructor.get();

Function<String, Greeting> nameConstructor = Greeting::new;
Greeting greetingWithName = nameConstructor.apply("this is my name");
```

noArgsConstructor는 멤버 변수를 생성하지 않고 Greeting 객체를 생성했다.

nameConstructor는 String parameter를 줌으로써 name 변수를 생성했다.

만약 2개 이상의 파라미터가 필요하다면 BiFunction을 사용하거나, 
3개 이상이 필요하다면 자체적인 정의가 필요하다


###다양한 형식의 인스턴스 메소드 참조

개인적으로 여기까진 이해가 잘되었으나 처음앤 이 사례가 잘 와닿지 않았는데

다른 레퍼런스나 모던 자바인 액션을 천천히 읽어보니 이해가 되었다.

이번엔 일반화한 예시를 먼저 보자.

```java
//lambda
(args, rest) -> args.instanceMethod(rest)

//Method Reference
ClassName::instanceMethod
```

구체적인 예시는 다음 코드를 살펴보자

```java
String[] strings = {"A","B","C","D"};
Arrays.sort(strings, String::compareToIgnoreCase);
```

루프에서 이 코드는 다음과 같이 돌아간다고 보면된다.
```java
strings[0].compareToIgnoreCase(strings[1]);
strings[1].compareToIgnoreCase(strings[2]);
strings[2].compareToIgnoreCase(strings[3]);
```

타이틀은 "다양한 형식의 인스턴스 메소드 참조" 이지만

"각각의 객체의 인스턴스 메소드 참조" 라는 식으로 이해하면 더욱 이해가 쉬울 것이다.
