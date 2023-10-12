package com.onlygod.chagogchagogbe.domain.product.domain;

import com.onlygod.chagogchagogbe.domain.product.domain.enums.ABCType;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    private String name;

    @NotNull
    @Column(columnDefinition = "INT")
    private Integer count;

    @NotNull
    @Column(columnDefinition = "BIGINT")
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private ABCType abcType;

    @Column(columnDefinition = "INT")
    private Integer aDate;

    @Column(columnDefinition = "INT")
    private Integer bCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private final List<IncomingProduct> incomingProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private final List<OutgoingProduct> outgoingProducts = new ArrayList<>();

    @Builder
    public Product(String name, Integer count, Long price, ABCType abcType, Integer aDate, Integer bCount, User user) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.abcType = abcType;
        this.aDate = aDate;
        this.bCount = bCount;
        this.user = user;
    }

    public Product addCount(Integer count) {
        this.count += count;
        return this;
    }

    public Product subCount(Integer count) {
        this.count -= count;
        return this;
    }
}
