package com.feedback.feedback.facade;

import com.feedback.feedback.dto.LoginDto;
import com.feedback.feedback.dto.RegisterDto;
import com.feedback.feedback.dto.UpdateEmailDto;
import com.feedback.feedback.dto.UpdatePasswordDto;
import com.feedback.feedback.model.User;

public interface UserFacade {

    User login(LoginDto dto);
    boolean register(RegisterDto dto);
    void updatePassword(UpdatePasswordDto dto);
    void updateEmail(UpdateEmailDto dto);

    User getUser(String username);

}
