package com.pjh.senicare.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pjh.senicare.dto.request.auth.IdCheckRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.repository.NurseRepository;
import com.pjh.senicare.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final NurseRepository nurseRepository;

    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
        
        String userId = dto.getUserId();

        try {

            boolean isExistedId = nurseRepository.existsByUserId(userId);
            if (isExistedId) return ResponseDto.duplicatedUserId();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        } 

        return ResponseDto.success();

    }

}
