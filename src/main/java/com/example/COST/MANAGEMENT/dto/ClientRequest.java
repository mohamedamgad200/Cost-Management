package com.example.COST.MANAGEMENT.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequest {
    @NotBlank(message = "Client Name should not be empty")
    @Size(min=10,message = "Client Name should be grater than 10 character")
    private String name;
    @NotBlank(message = "Client Email should not be empty")
    @Email(message = "Write a valid email")
    private String email;
    @NotBlank(message = "Client Phone should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String phone;
}
