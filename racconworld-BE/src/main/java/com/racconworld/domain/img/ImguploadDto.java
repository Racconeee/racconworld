package com.racconworld.domain.img;

import com.racconworld.domain.test.Test;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ImguploadDto {



    private String Filename; // 0202에 변경
    private String Filepath;
    private Test result_to_test;

    public ImguploadDto(String filename, String filepath, Test result_to_test) {
        Filename = filename;
        Filepath = filepath;
        this.result_to_test = result_to_test;
    }
    public String getFilename() {
        return Filename;
    }

    public String getFilepath() {
        return Filepath;
    }

    public Test getResult_to_test() {
        return result_to_test;
    }

}
