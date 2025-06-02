package com.newsfeed.testagram.domain.post.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;


@Getter
//@RequiredArgsConstructor
public class PostListResponseDto {

    private final int status;
    private final String message;
    private final Page<PostContentDto> posts;

    public PostListResponseDto(int status, String message, Page<PostContentDto> posts) {
        this.status = status;
        this.message = message;
        this.posts = posts;
    }

    //
    public static PostListResponseDto successOf(Page<PostContentDto> posts){
        return new PostListResponseDto(200, "전체 조회가 완료 되었습니다.", posts);
    }
}

