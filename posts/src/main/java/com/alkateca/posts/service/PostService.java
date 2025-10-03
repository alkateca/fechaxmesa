package com.alkateca.posts.service;

import com.alkateca.posts.dto.PostRequestDTO;
import com.alkateca.posts.model.Post;
import com.alkateca.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(PostRequestDTO postRequestDTO, String userId) {

        Post newPost = new Post();

        newPost.setTitle(postRequestDTO.title());
        newPost.setContent(postRequestDTO.content());
        newPost.setTags(postRequestDTO.tags());
        newPost.setUserId(Long.valueOf(userId));

        return postRepository.save(newPost);
    }

    public Post updatePost(Long id, PostRequestDTO postRequestDTO, String userId) {

        Post post = postRepository.findById(id).orElse(null);

        if(post == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Post not found");
        }
        if (!post.getUserId().equals(Long.valueOf(userId))) {
            throw new AccessDeniedException("User does not have permission to update this post");
        }

        if(postRequestDTO.content() != null) {
            post.setContent(postRequestDTO.content());
        }
        if (postRequestDTO.title() != null) {
            post.setTitle(postRequestDTO.title());
        }
        if (postRequestDTO.tags() != null) {
            post.setTags(postRequestDTO.tags());
        }
        return postRepository.save(post);

    }

    public void deletePost(Long id, String userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AccessDeniedException("Access denied"));

        postRepository.deleteById(id);
    }

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

}
