package com.nima.talentapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "A talent profile")
@JsonPropertyOrder({"id", "name", "title", "profile_text", "email", "phone", "city", "country", "github", "linkedin"})
public class Talent {

    @Schema(description = "Unique identifier", format = "uuid")
    private String id;

    @Schema(description = "Full name")
    private String name;

    @Schema(description = "Job title")
    private String title;

    @Schema(description = "Short introduction to the talent")
    @JsonProperty("profile_text")
    private String profileText;

    @Schema(description = "Email address", format = "email")
    private String email;

    @Schema(description = "Phone number")
    private String phone;

    @Schema(description = "City")
    private String city;

    @Schema(description = "Country")
    private String country;

    @Schema(description = "GitHub profile URL", format = "uri", nullable = true)
    private String github;

    @Schema(description = "LinkedIn profile URL", format = "uri", nullable = true)
    private String linkedin;

}
