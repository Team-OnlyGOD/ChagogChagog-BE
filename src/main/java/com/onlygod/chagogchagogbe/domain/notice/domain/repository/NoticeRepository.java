package com.onlygod.chagogchagogbe.domain.notice.domain.repository;

import com.onlygod.chagogchagogbe.domain.notice.domain.Notice;
import com.onlygod.chagogchagogbe.domain.notice.domain.repository.vo.QQueryNoticesVO;
import com.onlygod.chagogchagogbe.domain.notice.domain.repository.vo.QueryNoticesVO;
import com.onlygod.chagogchagogbe.domain.notice.presentation.dto.response.QueryNoticesResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.onlygod.chagogchagogbe.domain.notice.domain.QNotice.notice;
import static com.onlygod.chagogchagogbe.domain.product.domain.QProduct.product;
import static com.onlygod.chagogchagogbe.domain.user.domain.QUser.user;

@RequiredArgsConstructor
@Repository
public class NoticeRepository {

    private final NoticeJpaRepository noticeJpaRepository;
    private final JPAQueryFactory queryFactory;

    public Notice saveNotice(Notice notice) {
        return noticeJpaRepository.save(notice);
    }

    public void saveAllNotice(List<Notice> notices) {
        noticeJpaRepository.saveAll(notices);
    }

    public List<QueryNoticesVO> queryNotices(Long userId) {
        return queryFactory
                .select(
                        new QQueryNoticesVO(
                                product.name,
                                notice.noticeStatus
                        )
                )
                .from(notice)
                .join(notice.product, product)
                .join(product.user, user)
                .where(user.id.eq(userId))
                .fetch();
    }
}
