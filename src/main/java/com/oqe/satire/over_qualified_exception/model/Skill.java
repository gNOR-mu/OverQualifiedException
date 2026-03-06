package com.oqe.satire.over_qualified_exception.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "url_image")
    private String urlImage;

    @Column(columnDefinition = "TEXT")
    private String features;

    @Column(name = "brand_id")
    private Long brandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToMany(mappedBy = "skills")
    private List<Customer> customers = new ArrayList<>();
}
