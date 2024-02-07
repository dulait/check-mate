# check-mate
[![GitHub Release](https://img.shields.io/github/v/release/dulait/check-mate?include_prereleases)](https://github.com/dulait/check-mate/releases)

check-mate is a Java validation library designed to help with validating various standardized numbers.<br>
the current version currently supports validation of:
- IBANs
- ISBN-10 numbers
- ISBN-13 numbers
- E-Mail addresses
- JMBG (Unique Master Citizen Number for Serbian citizens)
- credit card numbers

## table of contents

- [installation](#installation)
- [usage](#usage)
- [contributing](#contributing)
- [releases](#releases)

## installation

to use check-mate in your Java project you can add it as a dependency in your Maven project's `pom.xml` file:
```xml
<dependency>
    <groupId>io.github.dulait</groupId>
    <artifactId>check-mate</artifactId>
    <version>1.1.0-alpha</version>
</dependency>
```
for Gradle projects, add the following to your `build.gradle` file
```groovy
implementation 'io.github.dulait:check-mate:1.1.0-alpha'
```

## usage

check-mate simplifies code validation in your Java applications. <br>
here's a basic example of how you can use it:

```java
import com.checkmate.validation.CreditCardValidator;

public class Main {
    public static void main(String[] args) {
        String creditCardNumber = "1234567812345670";

        if (CreditCardValidator.getInstance().isValid(creditCardNumber)) {
            System.out.println("Credit card number is valid!");
        } else {
            System.out.println("Invalid credit card number.");
        }
    }
}
```
some of the numbers that can be validated have check-digits (checksum digits) that are calculated using various algorithms. <br>
that means that inserting data can be tricky if that last digit isn't valid. <br>

let's examine an example where the last digit of an ISBN-10 number might be invalid:
```java
import com.checkmate.validation.CreditCardValidator;

public class Main {
    public static void main(String[] args) {
        // the given isbn10 number has an invalid check-digit
        String isbn10 = "0-596-52068-7";

        // this method generates a valid isbn10 number, if every part of the number is valid except the check-digit
        System.out.println(ISBNValidator.validateISBN10(isbn10)); // 0-596-52068-9
    }
}
```


## contributing

as this is a solo-project and there might be fundamental mistakes, all contributing is welcome. <br>
to contribute to the project just simply:
- fork the repository
- create a new branch for your feature or bug fix: `git checkout -b feature/my-feature`
- commit your changes: `git commit -m 'adequate comment`
- push to your branch `git push origin feature/my-feature`
- create a pull request explaining your changes and improvements (be as detailed as possible)

also, make sure to look at the [code of conduct](CODE_OF_CONDUCT.md) & feel free to contact me for additional information if needed.
