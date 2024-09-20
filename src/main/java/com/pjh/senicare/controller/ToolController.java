package com.pjh.senicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjh.senicare.dto.request.tool.PostToolRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.tool.GetToolListResponseDto;
import com.pjh.senicare.dto.response.tool.GetToolResponseDto;
import com.pjh.senicare.service.ToolService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tool")
@RequiredArgsConstructor
public class ToolController {
    
    private final ToolService toolService;

    @PostMapping(value={"", "/"})
    public ResponseEntity<ResponseDto> postTool(
        @RequestBody @Valid PostToolRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = toolService.postTool(requestBody);
        return response;
    }

    @GetMapping(value={"", "/"})
    public ResponseEntity<? super GetToolListResponseDto> getToolList() {
        ResponseEntity<? super GetToolListResponseDto> response = toolService.getToolList();
        return response;
    }

    @GetMapping("/{toolNumber}")
    public ResponseEntity<? super GetToolResponseDto> getTool(
        @PathVariable("toolNumber") Integer toolNumber
    ) {
        ResponseEntity<? super GetToolResponseDto> response = toolService.getTool(toolNumber);
        return response;
    }

}
