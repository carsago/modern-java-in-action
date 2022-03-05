# Chap 2

### 동작 파라미터(Behavior Parameter)를 통해 요구사항을 손쉽게 변경, 추가하자!

Apple이라는 객체가 있을때 색이 GREEN인 APPLE을 필터링 하는 메소드를 만들어 보자.


```Java
public class Apple {

    private int weight;
    private Color color;


    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }
}
```

```java
public enum Color {
    GREEN,
    RED
}
```


```java
public static List<Apple> filterGreenApple(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
        if (GREEN.equals(apple.getColor())) {
            result.add(apple);
        }
    }
    return result;
}
```

딱히 어렵지도 않고 원하는 요구사항을 정확히 만족한다.

이를 좀 더 범용성있게 사용하기 위해 Color를 parameter로 사용해 다른 색의 apple도 filtering 할 수 있게 해보자

```java
public static List<Apple> filterAppleByColor(List<Apple> inventory, Color color) {
    List<Apple> result = new ArrayList<>();

    for (Apple apple : inventory) {
        if (color.equals(apple.getColor())) {
            result.add(apple);
        }
    }
    return result;
}
```

좀 더 범용성 있는 코드가 완성되었다.

하지만 이번엔 색이 아닌 weight가 150 이상은 apple을 필터링 해달라는 요구사항이 추가되었다고 생각해보자.



```java
public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
    List<Apple> result = new ArrayList<>();
    
    for (Apple apple : inventory) {
        if (apple.getWeight() >= weight) {
            result.add(apple);
        }
    }
    return result;
}
```

위 코드를 살짝만 수정하여서 새로운 요구사항을 만족시켰다.

하지만 색 필터와 너무 많은 부분이 코드가 중복된다.

또한 다른 조건에 대한 요구가 추가되면 또 복붙을 하고 변경해야한다.

심지어 모든 필터들에 대해 동일하게 요구사항이 바뀐다면 하나 하나 수정을 해야한다..


이러한 문제를 해결하기 위해 파라미터를 통해 아예 선택 조건을 결정해보자!!

다음은 특정한 조건의 만족여부에 따라 boolean 값을 반환하는 인터페이스와 그 구현체들이다.

```java
public interface ApplePredicate {

    boolean test(Apple apple);
}

...

public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() >= 150;
    }
}

...

public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }
}
```

ApplePredicate를 parameter를 받는 메소드를 추가해보자 !! 

```java
public static List<Apple> filterApples(List<Apple> inventory,
                                       ApplePredicate predicate) {
    List<Apple> result = new ArrayList<>();
    
    for (Apple apple : inventory) {
        if (predicate.test(apple)) {
            result.add(apple);
        }
    }
    return result;
}
```

이제 고민하던 사항이 해결되었다.

만약 새로운 요구사항이 생긴다면 기존의 코드를 변경할 필요가 없다. 

요구사항에 맞는 구현체를 추가하기만 하면 된다.

다음은 이를 실제 활용하는 코드이다.


```java
public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(
            new Apple(150, GREEN),
            new Apple(180, RED),
            new Apple(120, RED)
    );

    List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
    List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
```

만약 굳이 구현체를 만들고 싶지 않고, 일회용으로 활용할 것 같다면 익명 클래스를 사용하는 방법도 있다.

```java
List<Apple> redAndLightApples = filterApples(inventory, new ApplePredicate() {
    @Override
    public boolean test(Apple apple) {
        return RED.equals(apple.getColor())
                && apple.getWeight() <= 120;
    }
});
```

굳이 구현체를 만들지 않아도 된다는 점은 편하다. 

하지만 익명 클래스를 활용하는 것은 더 복잡한 코드속에서, 많은 파라미터가 주어진다면 이해하기 어려워진다.

만약 lambda 문법을 사용해서 익명 클래스를 구현한다면 더욱 짧고 가독성 있는 코드를 사용할 수 있다.

```java
List<Apple> redAndLightApplesByLambda = filterApples(inventory,
        a -> RED.equals(a.getColor()) && a.getWeight() <= 120);
```

위와 정확히 동일한 역할을 하는 코드이지만 훨씬 짧아졌다.


이제 마지막으로 기존에 사용하던 코드를 다형성을 활용해서 사용해보자

지금 코드는 요구사항에 대해선 유연하게 변경가능하지만 Apple에게만 적용할 수 있다.

이를 Banana, Melon 등에도 적용할 수 있게 변경해보자!!

```java

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

```

