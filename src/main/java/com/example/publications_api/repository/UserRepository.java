package com.example.publications_api.repository;

import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
import com.example.publications_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT p FROM Post p WHERE p.userId.idUser = :idUser AND p.archived = false")
    List<PostResponseDTO> findAllPublicPostsFromUser(
            @Param("idUser") Long idUser
    );

    @Query("SELECT c FROM Comment c WHERE c.postId.userId.idUser = :idUser AND c.postId.archived = false")
    List<CommentResponseDTO> findAllCommentOnPublicPostFromUser(
            @Param("idUser") Long idUser
    );

}
