package com.example.publications_api.repository;

import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
        SELECT new com.example.publications_api.dto.comment.CommentResponseDTO(
                c.idComment, c.postId.idPost, c.userId.username, c.message, c.createdAt, c.updatedAt
                )
                        FROM Comment c
                                WHERE c.postId.idPost = :idPost""")
    List<CommentResponseDTO> findAllCommentsByPost(
            @Param("idPost") Long idPost
    );
}
