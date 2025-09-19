package com.alkateca.posts.controller;

import com.alkateca.posts.dto.PostRequestDTO;
import com.alkateca.posts.dto.PostResponseDTO;
import com.alkateca.posts.model.Post;
import com.alkateca.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        Post newPost = postService.createPost(postRequestDTO);
        PostResponseDTO responseDTO = new PostResponseDTO(
                newPost.getId(),
                newPost.getTitle(),
                newPost.getContent(),
                newPost.getTags(),
                newPost.getUserId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @RequestBody PostRequestDTO postRequestDTO) {
        Post updatedPost = postService.updatePost(id, postRequestDTO);

        PostResponseDTO responseDTO = new PostResponseDTO(
                updatedPost.getId(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getTags(),
                updatedPost.getUserId()

        );

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public void PostDelete(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/{userId}")
    public Post getUserPosts(@PathVariable Long userId) {
        return postService.findByUserId(userId);
    }
}
