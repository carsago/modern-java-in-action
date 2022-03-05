# 메소드 참조를 알아보자


```java
inventory.sort(comparing(a ->  a.getWeight()));

inventory.sort(comparing(Apple::getWeight));
```

chap03.md에서 나왔던 메소드 참조 활용이다.

뭔가 알거같으면서도 모르겠는 느낌이다.

