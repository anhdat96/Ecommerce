package com.example.demo.service.mapper.impl;

import com.example.demo.models.Orders;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.dto.*;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserMapperImpl extends BaseMapper implements IUserMapper {
    @Autowired
    private IRoleRepository roleRepo;
    @Autowired
    private IOrderRepository orderRepo;

    /* convert tu entity -->DTO*/
    @Override
    public UserDTO convertToDTO(User input) {
        UserDTO output = this.tranferData(input, UserDTO.class);

        //config user
        this.displayConfig(output);

        return output;
    }

    //region cấu hình cách hiển thị thông tin order table
    private void displayConfig(UserDTO userDTO) {
        // config role of user
        for (RoleDTO roleDTO : userDTO.getRoles()) {
            if (roleDTO != null) {
                roleDTO.setUsers(new HashSet<>());
            }
        }

        // config order of user
        for (OrderDTO orderDTO : userDTO.getOrdersList()) {
            if (orderDTO == null) continue;
            ;

            orderDTO.setUser(null);
            // config order detail
            for (OrderDetailDTO orderDetailDTO : orderDTO.getOderDetailList()) {
                if (orderDetailDTO == null) continue;

                orderDetailDTO.setOrders(null);
                // config product
                ProductDTO productDTO = orderDetailDTO.getProducts();
                productDTO.setOderDetailList(new HashSet<>());

                //config product category
                ProductCategoryDTO productCategoryDTO = productDTO.getProductCategories();
                if (productCategoryDTO != null) {
                    productCategoryDTO.setProductsList(new HashSet<>());
                }
            }
        }


    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public User convertToEntity(UserDTO input) {
        User output = this.tranferData(input, User.class);

        output.setRoles(this.getDataById(input.getRole_ids(), roleRepo, Role.class));
        output.setOrdersList(this.getDataById(input.getOrder_ids(), orderRepo, Orders.class));

        return output;
    }
}