package com.study.springboot.multi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
   
   // @Bean : 메소드에 어노테이션을 붙이면 return되는 객체를 bean으로 등록
   @Bean
   public Person Person1() {
      // setter()메소드를 이용한 의존성 주입
      Person person1 = new Person();
      person1.setName("홍길동");
      person1.setNickname("아버지라부르지못함");
      person1.setPrinter(new PrinterA());
      
      return person1;
   }
   
   @Bean(name="hello") //return의 객체 이름이 없는 경우 @Bean옆에 이름을 명시
   					  //Bean 에 있는 name이기때문에 Person의 name이랑 다른거임
   public Person Person2() {
      // constructor 를 이용한 의존성 주입
       return new Person("이순신", "장군", new PrinterA());
   }
   // Person hello = new Person("이순신", "장군", new PrinterA()); @Bean(name="hello")같은뜻
   
   @Bean
   public PrinterA printerA() {
      return new PrinterA();
   }
   
   @Bean
   public PrinterB printerB() {
      return new PrinterB();
   }
}
