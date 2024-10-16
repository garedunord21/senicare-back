package com.pjh.senicare.service;

import org.springframework.http.ResponseEntity;

import com.pjh.senicare.dto.request.nurse.PatchNurseRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.nurse.GetChargedCustomerResponseDto;
import com.pjh.senicare.dto.response.nurse.GetNurseListResponseDto;
import com.pjh.senicare.dto.response.nurse.GetNurseResponseDto;
import com.pjh.senicare.dto.response.nurse.GetSignInResponseDto;

public interface NurseService {
    
    ResponseEntity<? super GetNurseListResponseDto> getNurseList();
    ResponseEntity<? super GetNurseResponseDto> getNurse(String userId); 
    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);
    ResponseEntity<ResponseDto> patchNurse(PatchNurseRequestDto dto, String userId);
    ResponseEntity<? super GetChargedCustomerResponseDto> getChargedCustomer(String nurseId);

}