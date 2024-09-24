package com.pjh.senicare.dto.response.nurse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pjh.senicare.common.object.Nurse;
import com.pjh.senicare.dto.response.ResponseCode;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.ResponseMessage;
import com.pjh.senicare.entity.NurseEntity;

import lombok.Getter;

@Getter
public class GetNurseListResponseDto extends ResponseDto {
    private List<Nurse> nurses;

    private GetNurseListResponseDto(List<NurseEntity> nurseEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.nurses = Nurse.getList(null);
    }

    public static ResponseEntity<GetNurseListResponseDto> success(List<NurseEntity> nurseEntities) {
        GetNurseListResponseDto responseBody = new GetNurseListResponseDto(nurseEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
