package com.onlygod.chagogchagogbe.domain.notice.service;

import com.onlygod.chagogchagogbe.domain.notice.domain.repository.NoticeRepository;
import com.onlygod.chagogchagogbe.domain.notice.domain.repository.vo.QueryNoticesVO;
import com.onlygod.chagogchagogbe.domain.notice.presentation.dto.response.QueryNoticesResponse;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryNoticesService {

    private final NoticeRepository noticeRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryNoticesResponse execute() {
        User user = userFacade.getCurrentUser();
        List<QueryNoticesVO> notices = noticeRepository.queryNotices(user.getId());
        return new QueryNoticesResponse(notices);
    }
}
