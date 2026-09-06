package com.railway.blockoptimizer.service;

import com.railway.blockoptimizer.domain.entity.Corridor;
import com.railway.blockoptimizer.dto.CorridorDTO;
import com.railway.blockoptimizer.exception.ResourceNotFoundException;
import com.railway.blockoptimizer.repository.CorridorRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CorridorService {

    private final CorridorRepository corridorRepository;

    public CorridorService(CorridorRepository corridorRepository) {
        this.corridorRepository = corridorRepository;
    }

    public List<CorridorDTO> getAllCorridors() {
        return corridorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CorridorDTO getCorridorById(UUID id) {
        Corridor corridor = corridorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found with ID: " + id));
        return mapToDTO(corridor);
    }

    public CorridorDTO createCorridor(CorridorDTO dto) {
        Corridor corridor = Corridor.builder()
                .code(dto.getCode().toUpperCase().trim())
                .name(dto.getName().trim())
                .sourceStation(dto.getSourceStation().toUpperCase().trim())
                .destinationStation(dto.getDestinationStation().toUpperCase().trim())
                .lengthKm(dto.getLengthKm())
                .maxSpeedKmh(dto.getMaxSpeedKmh())
                .numberOfTracks(dto.getNumberOfTracks())
                .status(dto.getStatus())
                .build();

        Corridor saved = corridorRepository.save(corridor);
        return mapToDTO(saved);
    }

    public Map<String, Object> getCorridorAvailability(UUID id) {
        Corridor corridor = corridorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found with ID: " + id));

        Map<String, Object> availability = new HashMap<>();
        availability.put("corridorId", corridor.getId());
        availability.put("code", corridor.getCode());
        availability.put("name", corridor.getName());
        availability.put("status", corridor.getStatus());
        availability.put("totalLengthKm", corridor.getLengthKm());
        availability.put("maxSpeedKmh", corridor.getMaxSpeedKmh());
        availability.put("availableCapacityPercent", corridor.getStatus().name().equals("ACTIVE") ? 85.0 : 40.0);
        return availability;
    }

    private CorridorDTO mapToDTO(Corridor corridor) {
        return CorridorDTO.builder()
                .id(corridor.getId())
                .code(corridor.getCode())
                .name(corridor.getName())
                .sourceStation(corridor.getSourceStation())
                .destinationStation(corridor.getDestinationStation())
                .lengthKm(corridor.getLengthKm())
                .maxSpeedKmh(corridor.getMaxSpeedKmh())
                .numberOfTracks(corridor.getNumberOfTracks())
                .status(corridor.getStatus())
                .build();
    }
}
