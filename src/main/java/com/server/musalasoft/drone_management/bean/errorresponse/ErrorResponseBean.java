package com.server.musalasoft.drone_management.bean.errorresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseBean {
    private String status;
    private List<String> description;
}
