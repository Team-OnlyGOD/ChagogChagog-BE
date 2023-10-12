package com.onlygod.chagogchagogbe.domain.notice.domain.repository;

import com.onlygod.chagogchagogbe.domain.notice.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class NoticeRepository {

    private final NoticeJpaRepository noticeJpaRepository;

    public Notice saveNotice(Notice notice) {
        return noticeJpaRepository.save(notice);
    }

    public void saveAllNotice(List<Notice> notices) {
        noticeJpaRepository.saveAll(notices);
    }
}
