package com.server.musalasoft.drone_management.bean.errorresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseBean implements Serializable {
    private static final long serialVersionUID = 5540170966046256693L;

    private String status;
    private List<String> description;
}
