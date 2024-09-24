package com.pjh.senicare.service;

import org.springframework.http.ResponseEntity;

import com.pjh.senicare.dto.response.nurse.GetNurseListResponseDto;
import com.pjh.senicare.dto.response.nurse.GetSignInResponseDto;

public interface NurseService {
    
    ResponseEntity<? super GetNurseListResponseDto> getNurseList();
    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);

}
