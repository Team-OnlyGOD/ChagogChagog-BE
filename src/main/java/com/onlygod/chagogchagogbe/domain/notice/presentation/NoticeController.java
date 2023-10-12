package com.onlygod.chagogchagogbe.domain.notice.presentation;

import com.onlygod.chagogchagogbe.domain.notice.presentation.dto.response.QueryNoticesResponse;
import com.onlygod.chagogchagogbe.domain.notice.service.QueryNoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeController {

    private final QueryNoticesService queryNoticesService;

    @GetMapping
    public QueryNoticesResponse queryNotices() {
        return queryNoticesService.execute();
    }
}
