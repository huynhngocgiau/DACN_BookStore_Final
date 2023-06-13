package vn.edu.hcmuaf.st.DACN_BookStore_2023.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorID;
    @Column
    private String name;
    @Column(name = "author_code")
    private String authorCode;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<BookEntity> books = new ArrayList<>();

}
