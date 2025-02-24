package com.dharmaraj.splitwise.dtos;

import com.dharmaraj.splitwise.models.Group;
import lombok.Data;

@Data
public class CreateGroupResponseDto extends ResponseDto {
    
    private Group group;
    
}
