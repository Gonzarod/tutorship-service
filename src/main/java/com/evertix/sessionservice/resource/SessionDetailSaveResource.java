package com.evertix.sessionservice.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SessionDetailSaveResource {
    @NotNull(message = "State cannot be null")
    @NotBlank(message = "State cannot be blank")
    @Size(max = 80)
    private String state;
}
