# check-mate
[![GitHub Release](https://img.shields.io/github/v/release/dulait/check-mate?include_prereleases)](https://github.com/dulait/check-mate/releases)

check-mate is a Java library designed to help with validating various standardized numbers.<br>
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

## installation

to use check-mate in your Java project you can add it as a dependency in your Maven `pom.xml` file, but you'll also need to use a PAT i generated for reading the maven package (since github doesn't allow unauthenticated requests to public maven packages, and uploading to maven central is too much work)
```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://dulait: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@maven.pkg.github.com/dulait/check-mate</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>io.github.dulait</groupId>
        <artifactId>check-mate</artifactId>
        <version>1.1.0-alpha</version>
    </dependency>
</dependencies>
```


for Gradle projects, add the following to your `build.gradle` file
```groovy
implementation 'io.github.dulait:check-mate:1.1.0-alpha'
```

if you want to add it as an external library go to the [releases](https://github.com/dulait/check-mate/releases) page and download the latest version. <br>
you can now simply add it into the `lib` folder of your project. <br>
make sure to include the `javadoc.jar` file with the `.jar` file.

## usage

check-mate simplifies code validation in your Java applications. <br>
here's a basic example of how you can use it:

```java
import com.checkmate.validation.CreditCardValidator;

public class Main {
    public static void main(String[] args) {
        String creditCardNumber = "4111111111111111";

        // the CreditCardValidator class uses the Luhn algorithm to check if the number is valid
        System.out.println(CreditCardValidator.getInstance().isValid(creditCardNumber)); // will print out true or false, in this case true
    }
}
```
some of the numbers that can be validated have check-digits (checksum digits) that are calculated using various algorithms. <br>
that means that inserting data can be tricky if that last digit isn't valid. <br>

let's examine an example where the last digit of an ISBN-10 number might be invalid:
```java
import com.checkmate.validation.ISBNValidator;

public class Main {
    public static void main(String[] args) {
        // the given isbn10 number has an invalid check-digit
        String isbn10 = "0-596-52068-7";

        // this method generates a valid isbn10 number, if every part of the number is valid except the check-digit
        System.out.println(ISBNValidator.getInstance().validateISBN10(isbn10)); // 0-596-52068-9
    }
}
```

## contributing

as this is a solo-project and there might be fundamental mistakes, all contributing is welcome. <br>
to contribute to the project just simply:
- fork the repository
- create a new branch for your feature or bug fix: `git checkout -b feature/my-feature`
- commit your changes: `git commit -m "appropriate comment"`
- push to your branch `git push origin feature/my-feature`
- create a pull request explaining your changes and improvements (be as detailed as possible)

also, make sure to look at the [code of conduct](CODE_OF_CONDUCT.txt) & feel free to contact me for additional information if needed.
