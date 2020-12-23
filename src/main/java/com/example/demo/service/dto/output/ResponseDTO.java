package com.example.demo.service.dto.output;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    int code ;
    int page ;
    int size ;
    String message;
    T data ;

    public ResponseDTO(){

    }
    public ResponseDTO(T t ){
        data =  t;
    }
}
