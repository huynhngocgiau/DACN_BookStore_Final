package vn.edu.hcmuaf.st.DACN_BookStore_2023.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class BookEntity {
    @Id
    private int id;
    @Column(name = "title", length = 200)
    private String title;

    private int year_public;

    private int total_page;
    @Column(name = "publisher", length = 200)
    private String publisher;

    private String description;

    private int price;
    @Column(name = "discount_percent")
    private double discountPercent;

    @Column(name = "quantity_sold")
    private int quantitySold;

    private LocalDate created_at;

    private LocalDate updated_at;
    private boolean active;
    private boolean news;
    private boolean hot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryID")
    private CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "authorID")
    private AuthorEntity author;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book",
            cascade = CascadeType.REMOVE)
    List<BookImageEntity> images = new ArrayList<>();

}
