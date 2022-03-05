# chap 3

## Lambda를 통해 동작 파라미터를 활용해보자!


Chap 3의 목표는 다음 코드를 이해하는 것이다.

```java
inventory.sort(comparing(Apple::getWeight));
```

지금은 잘 이해가 안가지만 한단계 한단계 이해해보자.


```java
void sort(Comparator<? super E> c)
```

sort 메소드는 다음과 같이 함수형 인터페이스인 Comparator를 파라미터로 받는다.

따라서 지금까지 배운 지식만으로도 간단하게 특정한 sort 조건을 만들 수 있다.

### 1단계

함수형 인터페이스에 대한 구현체를 통해 comparator를 사용해보자

```java
import part1.chap02.behavparam.Apple;

public class AppleComparator implements Comparable<Apple> {
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
}

inventory.sort(new AppleComparator());
```


### 2 ~ 3 단계

우리는 익명 클래스와 Lambda를 통해 이 코드를 더욱 간소화할 수 있는 방법을 배웠다.

```java
inventory.sort(new Comparator<Apple>() {
     public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
});
```

람다로 더욱 축약해보자

```java
inventory.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight));
```

Comparator의 comarping 메소드를 통해 반복되는 부분을 축약할 수 있다
(a1 ,a2 모두 같은 역할인데 반복됨)


```java
Comparator<Apple> c = Comparator.comparing(a -> a.getWeight());
```

이 부분이 잘 이해가 가지 않는다면 다음 코드를 통해 comparaing 메소드를 살펴보자.

```java
public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
        Function<? super T, ? extends U> keyExtractor)
{
    Objects.requireNonNull(keyExtractor);
    return (Comparator<T> & Serializable)
        (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
}
```

comparing 메소드를 바탕으로 코드를 축약하자!

```java
inventory.sort(comparing(a ->  a.getWeight()));
```

### 4단계

메소드 참조를 통해 더 코드를 간소화할 수 있다.

```java
inventory.sort(comparing(Apple::getWeight));
```

메소드 참조에 관한 부분은 다음 링크를 통해 공부해보자! 

- https://github.com/carsago/modern-java-in-action/blob/main/src/main/java/part1/chap03/chap03_method_reference.md




