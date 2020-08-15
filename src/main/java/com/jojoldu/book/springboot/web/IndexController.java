package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String home(Model model){
        List<PostsListResponseDto> allDesc = postsService.findAllDesc();
        model.addAttribute("posts",allDesc);

        //머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로외
        //뒤의 파일 확장자는 자동으로 지정됩니다. ( /template ) 까지

         return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

}
