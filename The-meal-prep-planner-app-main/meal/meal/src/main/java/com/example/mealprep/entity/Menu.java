package com.example.mealprep.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private double price;
}
