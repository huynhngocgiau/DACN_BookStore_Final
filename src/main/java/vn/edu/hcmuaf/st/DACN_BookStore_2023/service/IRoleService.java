package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;


import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.RoleDTO;

public interface IRoleService {
    public RoleDTO findRolebyName(String name);
}
