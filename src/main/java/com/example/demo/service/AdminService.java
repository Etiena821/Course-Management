package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;

public interface AdminService {
    Admin verifyLogin(UserDTO userDTO) throws Exception;
}
