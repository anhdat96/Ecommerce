package com.example.demo.service.mapper.impl;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.dto.*;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;

@Component
public class RoleMapperImpl extends BaseMapper implements IRoleMapper {
    @Autowired
    private IRoleRepository roleRepo;
    @Autowired
    private IUserRepository userRepo;

    /* convert tu entity -->DTO*/
    @Override
    public RoleDTO convertToDTO(Role input) {
        RoleDTO output = this.tranferData(input, RoleDTO.class);

        this.displayConfig(output);

        return output;
    }

    //region cấu hình cách hiển thị thông tin order table
    private void displayConfig(RoleDTO roleDTO) {
        //config user
        for (UserDTO userDTO : roleDTO.getUsers()) {
            userDTO.setRoles(new HashSet<>());
            // config order of user
            for (OrderDTO orderDTO : userDTO.getOrdersList()) {
                orderDTO.setUser(null);
                // config order detail of order
                for (OrderDetailDTO orderDetailDTO : orderDTO.getOderDetailList()) {
                    orderDetailDTO.setOrders(null);
                    // config product of order detail
                    ProductDTO productDTO = orderDetailDTO.getProducts();
                    if (productDTO != null) {
                        productDTO.setOderDetailList(new HashSet<>());
                        ProductCategoryDTO productCategoryDTO = productDTO.getProductCategories();
                        if (productCategoryDTO != null) {
                            productCategoryDTO.setProductsList(new HashSet<>());
                        }
                    }

                }
            }
        }
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public Role convertToEntity(RoleDTO input) {
        Role output = this.tranferData(input, Role.class);

        output.setUsers(this.getDataById(input.getUser_ids(), userRepo, User.class));

        return output;
    }
}