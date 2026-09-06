package com.railway.blockoptimizer.service;

import com.railway.blockoptimizer.domain.entity.BlockPlan;
import com.railway.blockoptimizer.domain.enums.PlanStatus;
import com.railway.blockoptimizer.dto.BlockPlanDTO;
import com.railway.blockoptimizer.exception.ResourceNotFoundException;
import com.railway.blockoptimizer.repository.BlockPlanRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PlanService {

    private final BlockPlanRepository blockPlanRepository;
    private final BlockRequestService blockRequestService;

    public PlanService(BlockPlanRepository blockPlanRepository, BlockRequestService blockRequestService) {
        this.blockPlanRepository = blockPlanRepository;
        this.blockRequestService = blockRequestService;
    }

    public List<BlockPlanDTO> getPlansByHorizon(String horizonType) {
        return blockPlanRepository.findByHorizonType(horizonType.toUpperCase()).stream()
                .map(this::mapToDTO)
                .toList();
    }

    public BlockPlanDTO approvePlan(UUID planId) {
        BlockPlan plan = blockPlanRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Block plan not found: " + planId));

        plan.setStatus(PlanStatus.APPROVED);
        BlockPlan updated = blockPlanRepository.save(plan);
        return mapToDTO(updated);
    }

    public BlockPlanDTO rejectPlan(UUID planId) {
        BlockPlan plan = blockPlanRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Block plan not found: " + planId));

        plan.setStatus(PlanStatus.REJECTED);
        BlockPlan updated = blockPlanRepository.save(plan);
        return mapToDTO(updated);
    }

    public BlockPlanDTO mapToDTO(BlockPlan plan) {
        return BlockPlanDTO.builder()
                .id(plan.getId())
                .planName(plan.getPlanName())
                .horizonType(plan.getHorizonType())
                .startDate(plan.getStartDate())
                .endDate(plan.getEndDate())
                .status(plan.getStatus())
                .createdByUserId(plan.getCreatedByUser() != null ? plan.getCreatedByUser().getId() : null)
                .createdByUserName(plan.getCreatedByUser() != null ? plan.getCreatedByUser().getFullName() : null)
                .approvedByUserId(plan.getApprovedByUser() != null ? plan.getApprovedByUser().getId() : null)
                .approvedByUserName(plan.getApprovedByUser() != null ? plan.getApprovedByUser().getFullName() : null)
                .scheduledTasks(blockRequestService.getAllBlockRequests())
                .createdAt(plan.getCreatedAt())
                .updatedAt(plan.getUpdatedAt())
                .build();
    }
}
