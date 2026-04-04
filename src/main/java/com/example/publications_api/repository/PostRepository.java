package com.example.publications_api.repository;

import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT c FROM Comment c WHERE c.postId.idPost = :idPost")
    List<Comment> findAllCommentsByPost(
            @Param("idPost") Long idPost
    );
}
