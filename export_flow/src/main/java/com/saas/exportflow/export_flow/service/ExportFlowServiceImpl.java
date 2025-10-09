package com.saas.exportflow.export_flow.service;

import com.saas.exportflow.export_flow.dto.WelcomeRequestDTO;
import com.saas.exportflow.export_flow.model.ExportRequest;
import com.saas.exportflow.export_flow.model.ScheduledExportJob;
import com.saas.exportflow.export_flow.model.UserInfo;
import com.saas.exportflow.export_flow.repository.ExportRequestRepository;
import com.saas.exportflow.export_flow.repository.ScheduledExportJobRepository;
import com.saas.exportflow.export_flow.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class ExportFlowServiceImpl implements ExportflowService{

    @Autowired
    private ExportRequestRepository exportRequestRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ScheduledExportJobRepository scheduledExportJobRepository;

    @Override
    public WelcomeRequestDTO getallExportRequestByUser(String username) {

        UserInfo userInfo = userInfoRepository.findByName(username)
                .orElseThrow(() -> new NoSuchElementException("User not found with name: " + username));
        List<ExportRequest> exportRequests = exportRequestRepository.findByUser(userInfo);
        List<ScheduledExportJob> scheduledExportJobs = scheduledExportJobRepository.findByUser(userInfo);
        return WelcomeRequestDTO.builder()
                .allExportRequest(exportRequests)
                .allScheduledExportJobs(scheduledExportJobs)
                .build();
    }
}
