package com.huntercodexs.common.util.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommonErrorModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
}
