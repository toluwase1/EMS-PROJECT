package ems.v2.service.implementation;

import ems.v2.model.Admin;
import ems.v2.repository.AdminRepository;
import ems.v2.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
private AdminRepository adminRepository;

@Autowired
public AdminServiceImpl(AdminRepository adminRepository){
    this.adminRepository = adminRepository;
}

    @Override
    public Admin getAdminByEmail(String adminEmail) {
        Optional<Admin> admin = adminRepository.findByEmail(adminEmail);
        if (admin.isPresent()) return admin.get();
        return null;
    }

    @Override
    public Admin login(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(email, password);
        if (admin.isPresent()) return admin.get();
        return null;
    }
}
