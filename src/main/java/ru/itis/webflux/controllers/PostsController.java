package ru.itis.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.webflux.entities.PostRecord;
import ru.itis.webflux.service.PostsService;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @GetMapping(value = "/posts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PostRecord> getAll() {
        return postsService.getAll();
    }
}