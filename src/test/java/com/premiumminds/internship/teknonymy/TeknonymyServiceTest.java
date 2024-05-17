package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.TeknonymyService;
import com.premiumminds.internship.teknonymy.Person;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TeknonymyServiceTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public TeknonymyServiceTest() {
  };

  @Test
  public void PersonNoChildrenTest() {
    Person person = new Person("John",'M',null, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "";
    assertEquals(result, expected);
  }

  @Test
  public void PersonOneChildTest() {
    Person person = new Person(
        "John",
        'M',
        new Person[]{ new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Holy";
    assertEquals(result, expected);
  }

  @Test
  public void PersonMultipleChildTest() {
    Person person = new Person(
      "A",
      'M',
      new Person[]{ 
        new Person("B",'M', null, LocalDateTime.of(2000, 1, 1, 0, 0)), 
        new Person("C",'M', null, LocalDateTime.of(2002, 1, 1, 0, 0)), 
        new Person("D",'F', null, LocalDateTime.of(2003, 1, 1, 0, 0)) 
      },
      LocalDateTime.of(1980, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of B";
    assertEquals(result, expected);
  }

  @Test
  public void PersonMultipleGrandChildrenTest() {
    Person person = new Person(
      "A",
      'M',
      new Person[]{ 
        new Person(
          "B",
          'M', 
          new Person[]{
            new Person("E",'M', null, LocalDateTime.of(2019, 1, 1, 0, 0))
          }, 
          LocalDateTime.of(2000, 1, 1, 0, 0)), 
        new Person("C",'M', null, LocalDateTime.of(2002, 1, 1, 0, 0)), 
        new Person(
          "D",
          'F', 
          new Person[]{
            new Person("F",'M', null, LocalDateTime.of(2018, 1, 1, 0, 0)),
            new Person("G",'M', null, LocalDateTime.of(2021, 1, 1, 0, 0)),
            new Person("H",'M', null, LocalDateTime.of(2022, 1, 1, 0, 0))
          }, 
          LocalDateTime.of(2003, 1, 1, 0, 0)) 
      },
      LocalDateTime.of(1980, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of F";
    assertEquals(result, expected);
  }

  @Test
  public void PersonGreatGrandChildrenTest() {
    Person person = new Person(
      "A",
      'M',
      new Person[]{ 
        new Person(
          "B",
          'M', 
          new Person[]{
            new Person("E",'M', null, LocalDateTime.of(2019, 1, 1, 0, 0))
          }, 
          LocalDateTime.of(2000, 1, 1, 0, 0)), 
        new Person("C",'M', null, LocalDateTime.of(2002, 1, 1, 0, 0)), 
        new Person(
          "D",
          'F', 
          new Person[]{
            new Person(
              "F",
              'M', 
              new Person[]{
                new Person("I",'M', null, LocalDateTime.of(2040, 1, 1, 0, 0)),
                new Person("J",'M', null, LocalDateTime.of(2042, 1, 1, 0, 0))
              }, 
              LocalDateTime.of(2018, 1, 1, 0, 0)),
            new Person(
              "G",
              'M',
              new Person[]{
                new Person("K",'M', null, LocalDateTime.of(2039, 1, 1, 0, 0)),
                new Person("L",'M', null, LocalDateTime.of(2041, 1, 1, 0, 0))
              }, 
              LocalDateTime.of(2021, 1, 1, 0, 0)),
            new Person("H",'M', null, LocalDateTime.of(2022, 1, 1, 0, 0))
          }, 
          LocalDateTime.of(2003, 1, 1, 0, 0)) 
      },
      LocalDateTime.of(1980, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandfather of K";
    assertEquals(result, expected);
  }
}