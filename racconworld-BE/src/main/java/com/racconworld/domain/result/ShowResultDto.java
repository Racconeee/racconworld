package com.racconworld.domain.result;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Valid
public class ShowResultDto {

    @NotBlank
    private String filepath;
    @NotBlank
    private String fileDownload;

    public ShowResultDto(String filepath ,String fileDownload) {
        this.filepath = filepath;
        this.fileDownload = fileDownload;
    }
}
