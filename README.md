# Text Calculator
TextCalculator is a Java class which provides a smart calculator.  

----
## Getting started
To start the calculator, run the function start() which provides a console.
```java
public class Main {
    public static void main(String[] args) {
        TextCalculator calc = new TextCalculator();
        calc.start();
    }
}
```
To finish the calculations, enter exit.

### Example
```text
> i = 0
> j = ++i
> x = i++ + 5
> y = (5 + 3) * 10
> i += y
> exit
{x=6.0, i=82.0, y=80.0, j=1.0}
```