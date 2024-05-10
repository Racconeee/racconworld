package com.racconworld.domain.result;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ShowResultDto {

    private String filepath;
    private String fileDownload;

    public ShowResultDto(String filepath ,String fileDownload) {
        this.filepath = filepath;
        this.fileDownload = fileDownload;
    }
}
