package com.onlygod.chagogchagogbe.domain.notice.presentation.dto.response;

import com.onlygod.chagogchagogbe.domain.notice.domain.repository.vo.QueryNoticesVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryNoticesResponse {
    private final List<QueryNoticesVO> notices;
}
