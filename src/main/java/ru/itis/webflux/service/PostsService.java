package ru.itis.webflux.service;

import reactor.core.publisher.Flux;
import ru.itis.webflux.entities.ApiPostRecord;
import ru.itis.webflux.entities.PostRecord;


public interface PostsService {
    Flux<PostRecord> getAll();
}