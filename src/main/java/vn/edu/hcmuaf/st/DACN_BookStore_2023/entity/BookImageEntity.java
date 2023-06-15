package vn.edu.hcmuaf.st.DACN_BookStore_2023.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookimage")
@Getter
@Setter
@NoArgsConstructor
public class BookImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageID;
    @Column
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookID")
    private BookEntity book;

}