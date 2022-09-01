package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Badges {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachments;
}
