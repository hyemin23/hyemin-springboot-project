package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    //insert
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //update
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        //조회했는데 결과가 있으면 오류를 , 없으면 requqestDto에서 넣어준 값으로 바꿔주기 위하여 posts로 받는다.
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("해당 게시글이 없습니다. id = " + id)));

        //update
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }
    //조회
    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(posts);
    }

}
