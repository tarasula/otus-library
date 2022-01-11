package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

        BookDao dao = context.getBean(BookDao.class);

//        System.out.println("All count " + dao.count());

//        dao.insert(new Book(1, "Война и Миир", new Author(1, ""), new Genre(1, "")));

//        System.out.println("All count " + dao.count());
//
//        Book ivan = dao.getById(2);
//
//        System.out.println("Ivan id: " + ivan.getId() + " name: " + ivan.getName());

        Console.main(args);
    }
}
