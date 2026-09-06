package com.railway.blockoptimizer.controller;

import com.railway.blockoptimizer.dto.ImportResultDTO;
import com.railway.blockoptimizer.service.DataImportService;
import com.railway.blockoptimizer.service.SupabaseStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final DataImportService dataImportService;
    private final SupabaseStorageService supabaseStorageService;

    public ImportController(DataImportService dataImportService, SupabaseStorageService supabaseStorageService) {
        this.dataImportService = dataImportService;
        this.supabaseStorageService = supabaseStorageService;
    }

    @PostMapping("/csv")
    public ResponseEntity<ImportResultDTO> importCSV(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String entityType) {
        return ResponseEntity.ok(dataImportService.importCSV(file, entityType));
    }

    @PostMapping("/json")
    public ResponseEntity<ImportResultDTO> importJSON(
            @RequestBody String jsonContent,
            @RequestParam("type") String entityType) {
        return ResponseEntity.ok(dataImportService.importJSON(jsonContent, entityType));
    }

    @PostMapping("/supabase-storage")
    public ResponseEntity<Map<String, Object>> uploadToSupabaseStorage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "path", defaultValue = "imports/") String path) {
        String destination = path + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        return ResponseEntity.ok(supabaseStorageService.uploadFileToSupabaseStorage(file, destination));
    }
}
