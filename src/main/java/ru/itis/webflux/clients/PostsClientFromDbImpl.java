package ru.itis.webflux.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.itis.webflux.entities.ApiPostRecord;
import ru.itis.webflux.entities.DbPostRecord;
import ru.itis.webflux.entities.PostRecord;
import ru.itis.webflux.repositories.PostsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostsClientFromDbImpl implements PostsClient {

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public Flux<PostRecord> getAll() {
        List<PostRecord> postsFromDb = postsRepository.findAll().stream().map(record ->
        PostRecord.builder()
                .id(record.getId().intValue())
                .body(record.getDescription())
                .userId(record.getAuthor_id().intValue())
                .from("PostsFromDb")
                .build()).collect(Collectors.toList());

        return Flux.fromIterable(postsFromDb);
    }
}
