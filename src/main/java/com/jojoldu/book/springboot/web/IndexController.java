package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final Logger log = LoggerFactory.getLogger(IndexController.class);


    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String home(Model model){
        List<PostsListResponseDto> allDesc = postsService.findAllDesc();
        model.addAttribute("posts",allDesc);


        //구글 로그인 성공 시 user로 가져올 수 있음.
        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName",user.getName());
        }

        //머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로외
        //뒤의 파일 확장자는 자동으로 지정됩니다. ( /template ) 까지
         return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
   public String postsUpdate(@PathVariable Long id, Model model){

        log.info("get 들어옴 ");
        PostsResponseDto byId = postsService.findById(id);

        model.addAttribute("post",byId);
        return "posts-update";
    }



}