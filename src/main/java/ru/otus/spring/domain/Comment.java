package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Content", nullable = false)
    private String content;

    @ManyToOne()
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ToString.Include
    @EqualsAndHashCode.Include
    public long getBookId() {
        if (book != null) {
            return book.getId();
        } else {
            return 0;
        }

    }

    public Comment(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}