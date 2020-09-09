<h2> How to move Slider on Website Using Selenium WebDriver and Testify using Assertion</h2>

There are many features on webpages where slider is one of them. Slider is more user friendly options than manaully typing while filtering. We can use selenium webdriver to move the slider on website like one we can see down below. Here, we are testing whether the Price slide move to all the way to left ($53). We can check the Range if it is "$53 - $53" or not after moving the slide to highest price, $53. We are using [XPath](https://en.wikipedia.org/wiki/XPath#:~:text=XPath%20(XML%20Path%20Language)%20is,Wide%20Web%20Consortium%20(W3C).) to get the slider on webpage.

<p align="center">
	<img width="700px" src="Image/webpage.png" align="center"/>
</p>

<br>

Lets start by following step:

Platform supporting Maven: [IntelliJ IDEA CE](https://www.jetbrains.com/idea/download/download-thanks.html?platform=mac&code=IIC)

1. Create a new Project

2. Choose [Maven](https://en.wikipedia.org/wiki/Apache_Maven#:~:text=Maven%20is%20a%20build%20automation,%2C%20Scala%2C%20and%20other%20languages.&text=Maven%20is%20built%20using%20a,application%20controllable%20through%20standard%20input.)

3. In Project SDK box, make sure you have latest version of java "JDK"

<p align="center">
	<img width="700px" src="https://github.com/kk289/Java-Automation-OpenBrowser/blob/master/OpenBrowser/Image/_1CreateProject.png" align="center"/>
</p>

4. Click Next, and give a name to your project, like "verifyDataProj"

5. Now create a package inside src/test/java/ and name it "verifyDataPack" then create a class inside it, name it "sliderScrollVerify".


<h4> What is Maven? </h4>

"Maven is a build automation tool used primarily for Java projects. Maven can also be used to build and manage projects written in C#, Ruby, Scala, and other languages.

Maven addresses two aspects of building software: how software is built, and its dependencies. An XML file describes the software project being built, its dependencies on other external modules and components, the build order, directories, and required plug-ins. It comes with pre-defined targets for performing certain well-defined tasks such as compilation of code and its packaging. 

Maven dynamically downloads Java libraries and Maven plug-ins from one or more repositories such as the Maven 2 Central Repository, and stores them in a local cache. Maven projects are configured using a Project Object Model, which is stored in a [pom.xml](https://github.com/kk289/Java-Slide-Scroller-Test-using-Selenium
/blob/master/pom.xml) file."

An example of [pom.xml](https://github.com/kk289/Java-Slide-Scroller-Test-using-Selenium
/blob/master/pom.xml) file looks like: 

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>OpenBrowser</artifactId>
    <version>1.0-SNAPSHOT</version>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
    </properties>

    <dependencies>

    	<dependency>
      		<groupId>junit</groupId>
      		<artifactId>junit</artifactId>
      		<version>4.11</version>
      		<scope>test</scope>
    	</dependency>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java-->
    	<dependency>
      		<groupId>org.seleniumhq.selenium</groupId>
      		<artifactId>selenium-java</artifactId>
      		<version>3.141.59</version>
      		<scope>test</scope>
    	</dependency>

    	<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-api -->
    	<dependency>
      		<groupId>org.seleniumhq.selenium</groupId>
      		<artifactId>selenium-api</artifactId>
     		<version>3.141.59</version>
      		<scope>test</scope>
    	</dependency>

    </dependencies>

</project>
```

9. Make sure to setup your "pom.xml" file like above. Just replace your pom.xml file with this [pom.xml](https://github.com/kk289/Java-Slide-Scroller-Test-using-Selenium
/blob/master/pom.xml)

10. Let's look at "sliderScrollVerify" class:

## sliderScrollVerify

```
package verifyDataPack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class verifySum {

    WebDriver wd;

    @Before
    // Open ChromeBrowser
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // chrome browser
        wd = new ChromeDriver();
        wd.get("https://www.w3schools.com/sql/sql_count_avg_sum.asp");
    }

    @Test
    public void VerifySum() {
        // Get number of rows in table
        List<WebElement> Row_count = wd.findElements(By.xpath("//*[@id='main']/div[6]/table/tbody/tr"));
        System.out.println("Number of Rows= " + Row_count.size());

        // Get number of Columns in table
        List<WebElement> Col_count = wd.findElements(By.xpath("//*[@id='main']/div[6]/table/tbody/tr[1]/th"));
        System.out.println("Number of Columns = " + Col_count.size() + "\n");

        // divided Xpath In three parts to pass Row_count and Col_count values.
        String first_part = "//*[@id='main']/div[6]/table/tbody/tr[";
        String second_part = "]/td[";
        String third_part = "]";

        // xpath for Row Header and Extract the data value using Xpath
        String final_xpath = first_part + 1 + third_part;
        String Table_data = wd.findElement(By.xpath(final_xpath)).getText();
        for (String data : Table_data.split(" ")) {
            System.out.print(String.format("%-28s ", data));
        }
        System.out.println();

        // Print out dash line under Row Header
        for (int i = 0; i < 28 * 6; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Loop to extract all data inside table using xpath
        for (int i = 2; i <= Row_count.size(); i++) {
            for (int j = 1; j <= Col_count.size(); j++) {
                String final_xpath_1 = first_part + i + second_part + j + third_part;
                String Table_data_2 = wd.findElement(By.xpath(final_xpath_1)).getText();
                System.out.print(String.format("%-28s ", Table_data_2));
            }
            System.out.println("");
        }

        // Testing
        Float sum = 0.0f;   // init sum
        for (int i = 2; i <= Row_count.size(); i++) {
            String final_xpath_1 = first_part + i + second_part + 6 + third_part;
            String Table_data_2 = wd.findElement(By.xpath(final_xpath_1)).getText();
            Float floatVal = Float.valueOf(Table_data_2).floatValue();
            sum += floatVal;
        }

        System.out.println("\nThe total sum of Price is: " + sum);

        Float expectedSum = 90.35f;   // Enter total sum of price
        Assert.assertEquals(sum, expectedSum);
        System.out.println("\nAssertion Passed Successfully.");
    }

    @After
    // Close the browser
    public void Close() throws InterruptedException {
        wd.quit();
    }
}
```

We are extracting the information of table first. Then We are testing whether the total sum of "Price" is matched with our expected sum (user input on number line 76) or not. If it equal with expected total sum, the test is passed, and if not, the program will fail to run.

Let's run the "verifySum" class. 

We get following result: 

<p align="center">
	<img width="800px" src="Image/testPassed.png" align="center"/>
</p>

<br>

The program runs successfully. It print out all information including how many rows (data) are there under the selected header. For testing part, it successfully matched the total sum with expected total price. 

We can also input some wrong total sum, and we should expect the following result where the test is failed because the expected sum doesnt match with actual total sum.

<p align="center">
	<img width="800px" src="Image/testfailed.png" align="center"/>
</p>

<br>

<b>Thank you. Let me know if you have any questions.</b>
