package com.DDIS.post.Command.application.controller;

import com.DDIS.post.Command.domain.aggregate.dto.PostResponseDTO;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.application.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("postCommandController")
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/find/{postNum}")
    public ResponseEntity<PostResponseDTO> getPrivatePost(
            @PathVariable Long postNum,
            @RequestParam(required = false) String password
    ) {
        log.info("비공개 게시글 조회 요청 - postNum: {}, 입력된 비밀번호: {}", postNum, password);

        Post post = postService.getPost(postNum, password);
        return ResponseEntity.ok(PostResponseDTO.fromEntity(post));
    }
}
