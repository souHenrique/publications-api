package com.example.publications_api.repository;

import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import com.example.publications_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id_user = :id_user AND p.archived = false")
    List<Post> findAllPublicPostsFromUser(
            @Param("id_user") Long id_user
    );

    @Query("SELECT c FROM Comment c WHERE c.post.user.id_user = :id_user AND c.post.archived = false")
    List<Comment> findAllCommentOnPublicPostFromUser(
            @Param("id_user") Long id_user
    );

}
