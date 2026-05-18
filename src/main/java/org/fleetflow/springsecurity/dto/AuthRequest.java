package org.fleetflow.springsecurity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotBlank(message = "le eamil est obligatoire")
    private String email;
    @NotBlank(message = "le mote de passe est obligatoire")
    private String password;
}
