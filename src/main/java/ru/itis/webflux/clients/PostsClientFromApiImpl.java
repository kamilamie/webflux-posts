package ru.itis.webflux.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.webflux.entities.ApiPostRecord;
import ru.itis.webflux.entities.PostRecord;

import java.util.Arrays;

@Component
public class PostsClientFromApiImpl implements PostsClient {

    private WebClient client;

    public PostsClientFromApiImpl(@Value("${posts.api.url}") String url) {
        client = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .baseUrl(url)
                .build();
    }

    @Override
    public Flux<PostRecord> getAll() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(ApiPostRecord[].class)) // преобразуем данные со стороннего сервреа в Publisher
                .flatMapIterable(Arrays::asList) // выполняем конвертацию данных с другого сервера в наши, возвращаем мы набор Publisher-ов(?) каждый из которых возвращает объект CovidStatistic
                .map(record ->
                        PostRecord.builder()
                                .id(record.getId())
                                .body(record.getBody())
                                .userId(record.getUserId())
                                .from("PostsApi")
                                .build());
    }
}
