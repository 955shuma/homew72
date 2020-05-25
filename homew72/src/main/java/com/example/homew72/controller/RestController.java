package com.example.homew72.controller;

import com.example.homew72.model.Comment;
import com.example.homew72.repository.CommentRepository;
import com.example.homew72.repository.ThemeRepository;
import com.example.homew72.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    UserRepository ur;

    @Autowired
    ThemeRepository tr;

    @Autowired
    CommentRepository cr;



    @PostMapping("/theme/addComment")
    public Comment addComment(@RequestParam("text") String text, @RequestParam("id") Integer id){
        var theme = tr.findThemeById(id);
        theme.setQty(theme.getQty()+1);
        tr.save(theme);
        Comment comment = Comment.builder()
                .ldt(LocalDateTime.now())
                .text(text)
                .theme(theme)
                .user(theme.getUser())
                .build();
        cr.save(comment);
        return comment;
    }

    @GetMapping("/chat/comment/{themeId}/{commentId}")
    public List<Comment> getComments(@PathVariable("themeId") Integer themeId, @PathVariable("commentId") Integer id){
        return cr.findAllByTheme_IdAndId(themeId, id);
    }
}
