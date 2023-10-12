package com.onlygod.chagogchagogbe.domain.notice.domain;

import com.onlygod.chagogchagogbe.domain.notice.domain.enums.NoticeStatus;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(columnDefinition = "VARCHAR(17)")
    private NoticeStatus noticeStatus;

    @Builder
    public Notice(Product product, NoticeStatus noticeStatus) {
        this.product = product;
        this.noticeStatus = noticeStatus;
    }
}
