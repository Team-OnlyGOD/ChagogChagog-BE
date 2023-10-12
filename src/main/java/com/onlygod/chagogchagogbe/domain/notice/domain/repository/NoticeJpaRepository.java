package com.onlygod.chagogchagogbe.domain.notice.domain.repository;

import com.onlygod.chagogchagogbe.domain.notice.domain.Notice;
import org.springframework.data.repository.CrudRepository;

public interface NoticeJpaRepository extends CrudRepository<Notice, Long> {
}
