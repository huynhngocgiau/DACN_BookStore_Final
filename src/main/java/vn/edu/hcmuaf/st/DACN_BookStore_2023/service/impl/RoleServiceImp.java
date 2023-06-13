package vn.edu.hcmuaf.st.DACN_BookStore_2023.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.converter.RoleConverter;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.RoleDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.RoleRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IRoleService;

@Service
public class RoleServiceImp implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleConverter roleConverter;


    @Override
    public RoleDTO findRolebyName(String name) {
        return roleConverter.toDTO(roleRepository.findByName(name));
    }
}
