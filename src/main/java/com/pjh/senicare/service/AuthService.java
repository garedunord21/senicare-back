package com.pjh.senicare.service;

import org.springframework.http.ResponseEntity;

import com.pjh.senicare.dto.request.auth.IdCheckRequestDto;
import com.pjh.senicare.dto.request.auth.TelAuthRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;

public interface AuthService {

    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto);
    
}
