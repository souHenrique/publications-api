package com.example.publications_api.repository;

import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT c FROM Comment c WHERE c.post.id_post = :id_post")
    List<Comment> findAllCommentsByPost(
            @Param("postId") Long id_post
    );
}
