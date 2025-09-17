package com.alkateca.posts.repository;

import com.alkateca.posts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
