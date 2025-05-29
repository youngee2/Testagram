package com.newsfeed.testagram.dto;

import com.newsfeed.testagram.entity.Post;
import lombok.Getter;
import org.springframework.data.domain.Page;


@Getter
//@RequiredArgsConstructor
public class PostListResponseDto {



    private int status;

    private String message;

    private Page<PostContentDto> posts;

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

