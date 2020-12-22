package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.Column;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ResponseDTO<T> implements Serializable {
    private String code;
    private Integer page;
    private Integer size;
    private String message;
    @JsonProperty("order")
    private T data;
}
