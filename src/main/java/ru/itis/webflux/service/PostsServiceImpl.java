package ru.itis.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.webflux.clients.PostsClient;
import ru.itis.webflux.entities.PostRecord;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private List<PostsClient> clients;

    @Override
    public Flux<PostRecord> getAll() {
        List<Flux<PostRecord>> fluxes =  clients.stream().map(this::getAll).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }

    private Flux<PostRecord> getAll(PostsClient client) {
        return client.getAll().subscribeOn(Schedulers.elastic());
    }
}