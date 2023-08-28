package com.example.week05d02blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "the username field is required.")
    @Size(min = 4, max = 64, message = "the username value must be or between 4 and 64 chars.") // this will apply check constraint by default
    @Column(nullable = false)
    private String title;


    @NotEmpty(message = "the body field is required.")
    @Size(min = 12, message = "the body must be more than or equal to 12 chars.") // this will apply check constraint by default
    @Column(nullable = false)
    private String body;


    // Relation

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;



}
