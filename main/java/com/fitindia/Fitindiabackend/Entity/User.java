package com.fitindia.Fitindiabackend.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone", nullable = false)
    private Long phone;
    @Column(name = "age", nullable = false)
    private Long age;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "weight", nullable = false)
    private Long weight;
    @Column(name = "height", nullable = false)
    private Long height;
    @Column(name = "progile_pic", nullable = false)
    private String profile_pic;
    @Column(name = "goal", nullable = false)
    private Goal goal;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Calories calories;
}
