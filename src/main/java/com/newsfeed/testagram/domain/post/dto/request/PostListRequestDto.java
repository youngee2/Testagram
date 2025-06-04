package com.newsfeed.testagram.domain.post.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PostListRequestDto {
    private int page;

    private int size;

}
