package com.pjh.senicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.nurse.GetNurseListResponseDto;
import com.pjh.senicare.dto.response.nurse.GetSignInResponseDto;
import com.pjh.senicare.entity.NurseEntity;
import com.pjh.senicare.repository.NurseRepository;
import com.pjh.senicare.service.NurseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NurseServiceImplement implements NurseService {

    private final NurseRepository nurseRepository;

    @Override
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
        
        NurseEntity nurseEntity = null;
        
        try {

            nurseEntity = nurseRepository.findByUserId(userId);
            if (nurseEntity == null) return ResponseDto.noExistUserId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInResponseDto.success(nurseEntity);
        
    }

    @Override
    public ResponseEntity<? super GetNurseListResponseDto> getNurseList() {

        List<NurseEntity> nurseEntities = new ArrayList<>();
        
        try {

            nurseEntities = nurseRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetNurseListResponseDto.success(nurseEntities);

    }
    
}