package ru.itis.webflux.clients;

import reactor.core.publisher.Flux;
import ru.itis.webflux.entities.PostRecord;

public interface PostsClient {
    Flux<PostRecord> getAll();
}
