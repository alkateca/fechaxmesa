package com.alkateca.posts.service;

import com.alkateca.posts.dto.PostRequestDTO;
import com.alkateca.posts.model.Post;
import com.alkateca.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(PostRequestDTO postRequestDTO) {

        Post newPost = new Post();

        newPost.setTitle(postRequestDTO.title());
        newPost.setContent(postRequestDTO.content());
        newPost.setTags(postRequestDTO.tags());
        newPost.setUserId(postRequestDTO.userId());

        return postRepository.save(newPost);
    }

    public Post updatePost(Long id, PostRequestDTO postRequestDTO) {

        Post post = postRepository.findById(id).orElse(null);
        if(post == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Post not found");
        }
        if(postRequestDTO.content() != null) {
            post.setContent(postRequestDTO.content());
            postRepository.save(post);
        }
        return post;
    }

    public Post findByUserId(Long userId) {
        return postRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404),"User not found"));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
