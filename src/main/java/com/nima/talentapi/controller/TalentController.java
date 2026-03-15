package com.nima.talentapi.controller;

import com.nima.talentapi.data.TalentStore;
import com.nima.talentapi.model.Document;
import com.nima.talentapi.model.Talent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talent")
@Tag(name = "Talent", description = "Talent API")
public class TalentController {

    private final TalentStore store;

    public TalentController(TalentStore store) {
        this.store = store;
    }

    @GetMapping
    @Operation(summary = "Get a list of talents")
    public ResponseEntity<List<Talent>> getAllTalents() {
        return ResponseEntity.ok(store.getAllTalents());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific talent")
    public ResponseEntity<Talent> getTalentById(
            @Parameter(description = "Talent ID") @PathVariable String id) {
        return store.getTalentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/documents")
    @Operation(summary = "Get documents for a specific talent")
    public ResponseEntity<List<Document>> getDocuments(
            @Parameter(description = "Talent ID") @PathVariable String id) {
        return store.getTalentById(id)
                .map(talent -> ResponseEntity.ok(store.getDocumentsByTalentId(id)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/documents/{documentId}")
    @Operation(summary = "Get a specific document for a talent")
    public ResponseEntity<Document> getDocument(
            @Parameter(description = "Talent ID") @PathVariable String id,
            @Parameter(description = "Document ID") @PathVariable String documentId) {
        return store.getTalentById(id)
                .flatMap(talent -> store.getDocumentById(id, documentId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
