package com.railway.blockoptimizer.service;

import com.railway.blockoptimizer.domain.entity.BlockRequest;
import com.railway.blockoptimizer.domain.entity.Conflict;
import com.railway.blockoptimizer.domain.entity.TrainSchedule;
import com.railway.blockoptimizer.domain.enums.BlockRequestStatus;
import com.railway.blockoptimizer.domain.enums.ConflictSeverity;
import com.railway.blockoptimizer.domain.enums.ConflictType;
import com.railway.blockoptimizer.dto.ConflictDTO;
import com.railway.blockoptimizer.repository.BlockRequestRepository;
import com.railway.blockoptimizer.repository.ConflictRepository;
import com.railway.blockoptimizer.repository.TrainScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConflictDetectionService {

    private final ConflictRepository conflictRepository;
    private final TrainScheduleRepository trainScheduleRepository;
    private final BlockRequestRepository blockRequestRepository;

    public ConflictDetectionService(ConflictRepository conflictRepository, TrainScheduleRepository trainScheduleRepository, BlockRequestRepository blockRequestRepository) {
        this.conflictRepository = conflictRepository;
        this.trainScheduleRepository = trainScheduleRepository;
        this.blockRequestRepository = blockRequestRepository;
    }

    @Transactional
    public List<Conflict> checkAndRecordConflicts(BlockRequest request) {
        List<Conflict> detectedConflicts = new ArrayList<>();

        List<TrainSchedule> overlappingSchedules = trainScheduleRepository.findOverlappingSchedules(
                request.getCorridor().getId(), request.getStartTime(), request.getEndTime());

        for (TrainSchedule schedule : overlappingSchedules) {
            if (isSpatialOverlap(request.getStartKm(), request.getEndKm(), schedule.getStartKm(), schedule.getEndKm())) {
                Conflict conflict = Conflict.builder()
                        .blockRequest(request)
                        .conflictingTrainSchedule(schedule)
                        .conflictType(ConflictType.TRAIN_BLOCK_OVERLAP)
                        .severity(schedule.getTrain() != null && schedule.getTrain().getPriorityLevel() <= 2 ? ConflictSeverity.HIGH : ConflictSeverity.MEDIUM)
                        .details(String.format("Block Request %s overlaps with Train %s (%s) from %s to %s",
                                request.getRequestNumber(),
                                schedule.getTrain() != null ? schedule.getTrain().getTrainNumber() : "Unknown",
                                schedule.getTrain() != null ? schedule.getTrain().getTrainName() : "",
                                schedule.getArrivalTime(), schedule.getDepartureTime()))
                        .status("UNRESOLVED")
                        .build();
                detectedConflicts.add(conflictRepository.save(conflict));
            }
        }

        List<BlockRequest> overlappingRequests = blockRequestRepository.findOverlappingRequests(
                request.getCorridor().getId(), request.getId(), request.getStartTime(), request.getEndTime());

        for (BlockRequest otherReq : overlappingRequests) {
            if (isSpatialOverlap(request.getStartKm(), request.getEndKm(), otherReq.getStartKm(), otherReq.getEndKm())) {
                Conflict conflict = Conflict.builder()
                        .blockRequest(request)
                        .conflictingBlockRequest(otherReq)
                        .conflictType(ConflictType.ASSET_DOUBLE_BOOKING)
                        .severity(ConflictSeverity.HIGH)
                        .details(String.format("Block Request %s overlaps with Block Request %s (%s - %s)",
                                request.getRequestNumber(), otherReq.getRequestNumber(),
                                otherReq.getStartTime(), otherReq.getEndTime()))
                        .status("UNRESOLVED")
                        .build();
                detectedConflicts.add(conflictRepository.save(conflict));
            }
        }

        if (!detectedConflicts.isEmpty() && request.getStatus() == BlockRequestStatus.PENDING) {
            request.setStatus(BlockRequestStatus.CONFLICT_DETECTED);
            blockRequestRepository.save(request);
        }

        return detectedConflicts;
    }

    public List<ConflictDTO> getAllConflicts() {
        return conflictRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private boolean isSpatialOverlap(Double start1, Double end1, Double start2, Double end2) {
        return Math.max(start1, start2) < Math.min(end1, end2);
    }

    public ConflictDTO mapToDTO(Conflict conflict) {
        return ConflictDTO.builder()
                .id(conflict.getId())
                .blockRequestId(conflict.getBlockRequest() != null ? conflict.getBlockRequest().getId() : null)
                .blockRequestNumber(conflict.getBlockRequest() != null ? conflict.getBlockRequest().getRequestNumber() : null)
                .conflictingTrainScheduleId(conflict.getConflictingTrainSchedule() != null ? conflict.getConflictingTrainSchedule().getId() : null)
                .conflictingTrainNumber(conflict.getConflictingTrainSchedule() != null && conflict.getConflictingTrainSchedule().getTrain() != null ?
                        conflict.getConflictingTrainSchedule().getTrain().getTrainNumber() : null)
                .conflictingBlockRequestId(conflict.getConflictingBlockRequest() != null ? conflict.getConflictingBlockRequest().getId() : null)
                .conflictingBlockRequestNumber(conflict.getConflictingBlockRequest() != null ? conflict.getConflictingBlockRequest().getRequestNumber() : null)
                .conflictType(conflict.getConflictType())
                .severity(conflict.getSeverity())
                .details(conflict.getDetails())
                .status(conflict.getStatus())
                .createdAt(conflict.getCreatedAt())
                .build();
    }
}
