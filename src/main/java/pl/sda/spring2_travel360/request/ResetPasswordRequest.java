package pl.sda.spring2_travel360.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    String oldPassword;
    String newPassword;
}
