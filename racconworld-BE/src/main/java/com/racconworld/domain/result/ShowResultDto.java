package com.racconworld.domain.result;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ShowResultDto {

    private String filepath;

    public ShowResultDto(String filepath) {
        this.filepath = filepath;
    }
}
