package com.newsfeed.testagram.domain.post.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostContentDto {
    private final Long postId;
    private final Long writerId;
    private final String nickName;
    private final String content;
}
