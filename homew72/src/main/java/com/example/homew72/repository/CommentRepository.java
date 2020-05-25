package com.example.homew72.repository;


import com.example.homew72.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment as c where c.theme.id=:id order by c.ldt asc")
    Page<Comment> findAllByTheme_Id(Integer id, Pageable page);

    @Query("select c from Comment as c where c.theme.id=?1 and c.id>?2 order by c.ldt asc")
    List<Comment> findAllByTheme_IdAndId(Integer themeId, Integer Id);
}
