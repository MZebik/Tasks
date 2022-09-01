package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentsByType {

    @JsonProperty("trello")
    private Trello trello;
}
