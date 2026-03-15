package com.nima.talentapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "A document belonging to a talent")
public class Document {

    @Schema(description = "Unique identifier", format = "uuid")
    private String id;

    @Schema(description = "Document name")
    private String name;

    @Schema(description = "Document content")
    private String content;

}
