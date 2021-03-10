package com.example.demo.service.serviceImpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;
import com.example.demo.repo.AdminRepo;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AdminServiceImpl implements AdminService {

    private AdminRepo adminRepo;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin verifyLogin(UserDTO userDTO) throws Exception {
        Optional<Admin> admin = adminRepo.findByEmailAndPassWord(userDTO.getEmail(), userDTO.getPassWord());
        if (admin.isPresent()) {
            return admin.get();
        }
        return null;
    }
}
