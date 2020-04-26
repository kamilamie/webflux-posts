package ru.itis.webflux.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiPostRecord {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}