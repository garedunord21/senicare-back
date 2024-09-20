package com.pjh.senicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pjh.senicare.dto.request.tool.PostToolRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.tool.GetToolListResponseDto;
import com.pjh.senicare.dto.response.tool.GetToolResponseDto;
import com.pjh.senicare.entity.ToolEntity;
import com.pjh.senicare.repository.ToolRepository;
import com.pjh.senicare.service.ToolService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolServiceImplement implements ToolService {

    private final ToolRepository toolRepository;

    @Override
    public ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto) {
        
        try {

            ToolEntity toolEntity = new ToolEntity(dto);
            toolRepository.save(toolEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetToolListResponseDto> getToolList() {
        
        List<ToolEntity> toolEntities = new ArrayList<>();

        try {

            toolEntities = toolRepository.findByOrderByToolNumberDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetToolListResponseDto.success(toolEntities);

    }

    @Override
    public ResponseEntity<? super GetToolResponseDto> getTool(Integer toolNumber) {
        
        ToolEntity toolEntity = null;

        try {

            toolEntity = toolRepository.findByToolNumber(toolNumber);
            if (toolEntity == null) return ResponseDto.noExistTool();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetToolResponseDto.success(toolEntity);

    }
    
}