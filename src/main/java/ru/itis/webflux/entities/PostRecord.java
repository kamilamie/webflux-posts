package ru.itis.webflux.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRecord {
    private Integer id;
    private Integer userId;
    private String body;
    private String from;
}
