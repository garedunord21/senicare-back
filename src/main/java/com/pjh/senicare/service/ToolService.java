package com.pjh.senicare.service;

import org.springframework.http.ResponseEntity;

import com.pjh.senicare.dto.request.tool.PostToolRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.tool.GetToolListResponseDto;
import com.pjh.senicare.dto.response.tool.GetToolResponseDto;

public interface ToolService {
    
    ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto);
    ResponseEntity<? super GetToolListResponseDto> getToolList();
    ResponseEntity<? super GetToolResponseDto> getTool(Integer toolNumber);
}