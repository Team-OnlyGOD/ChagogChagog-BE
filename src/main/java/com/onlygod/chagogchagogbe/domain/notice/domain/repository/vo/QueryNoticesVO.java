package com.onlygod.chagogchagogbe.domain.notice.domain.repository.vo;

import com.onlygod.chagogchagogbe.domain.notice.domain.enums.NoticeStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class QueryNoticesVO {

    private final String name;
    private final NoticeStatus noticeStatus;

    @QueryProjection
    public QueryNoticesVO(String name, NoticeStatus noticeStatus) {
        this.name = name;
        this.noticeStatus = noticeStatus;
    }
}
