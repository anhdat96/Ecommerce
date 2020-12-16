package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class OrderMapper extends BaseMapper {
    private final IOrderDetailRepository orderDetailRepo;
    private final IUserRepository userRepo;

    /* convert tu entity -->DTO*/
    public OrderDTO convertToDTO(Orders order) {
        OrderDTO orderDTO = this.tranferData(order, OrderDTO.class);

        this.displayConfig(orderDTO);

        return orderDTO;
    }

    //region cấu hình cách hiển thị thông tin order table
    private void displayConfig(OrderDTO orderDTO) {
        //config order detail
        for (OrderDetailDTO orderDetailDTO : orderDTO.getOderDetailList()) {
            //config order of order detail
            orderDetailDTO.setOrders(null);

            //config product of order detail
            ProductDTO productDTO = orderDetailDTO.getProducts();
            productDTO.setOderDetailList(new HashSet<>());
            productDTO.getProductCategories().setProductsList(new HashSet<>());
        }

        //config user
        UserDTO userDTO = orderDTO.getUser();
        if (null != userDTO) {
            userDTO.setOrdersList(new HashSet<>());

            for (RoleDTO roleDTO : userDTO.getRoles()) {
                roleDTO.setUsers(new HashSet<>());
            }
        }
    }
    //endregion

    /* convert tu DTO --> Entity*/
    public Orders convertToEntity(OrderDTO orderDTO) {
        Orders order = this.tranferData(orderDTO, Orders.class);

        order.setOderDetailList(this.getSetDataByIds(orderDTO.getOrderDetail_ids(), orderDetailRepo, OderDetail.class));
        order.setUser(this.getDataById(orderDTO.getUser_id(), userRepo, User.class));
        this.updateRelationTable(order);

        return order;
    }

    //region inner convertToEntity methods
    private void updateRelationTable(Orders order) {
        this.updateOrderDetailTable(order);
        this.updateUserTable(order);
    }

    //region inner updateRelationTable methods
    private void updateUserTable(Orders order) {
        User user = order.getUser();
        if (null != user) {
            user.getOrdersList().add(order);
            userRepo.save(user);
        }
    }

    private void updateOrderDetailTable(Orders order) {
        for (OderDetail orderDetail : order.getOderDetailList()) {
            orderDetail.setOrders(order);
            orderDetailRepo.save(orderDetail);
        }
    }
    //endregion
    //endregion
}