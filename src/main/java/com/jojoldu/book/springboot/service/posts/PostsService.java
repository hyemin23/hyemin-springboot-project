package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    //목록 가져오기
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        //posts 타입으로 들어오는것을 생성자로 post를 넣어줘서 PostsListResponseDto 타입으로 변환
        //즉, Posts의 Stream을 map을 통하여 PostListResponseDto로 변환 -> List로 변환하는 메소드 입니다.
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new)
                                .collect(Collectors.toList());

    }

    //석제
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow( () ->new IllegalArgumentException("해당 게시글이 없습니다. id  = "+id));

        postsRepository.delete(posts);
    }

}
