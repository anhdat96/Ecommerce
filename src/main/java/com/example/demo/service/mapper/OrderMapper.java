package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.OrderDetailDTO;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*nguyentrong đề xuất: thêm class abstract Mapper cha bao gồm:
1. attributes: ModelMapper
2. methods:
- convertToDTO()
- convertToEntity()
**note: nếu thêm class abstract Mapper cha thì cần thêm class abstract DTO cha*/
@Service
@RequiredArgsConstructor
public class OrderMapper {
    private final IOrderDetailRepository orderDetailRepo;
    private final IUserRepository userRepo;
    ModelMapper modelMapper = new ModelMapper();
    /* convert tu entity -->DTO*/

    public OrderDTO convertToDTO(Orders order) {
        OrderDTO dto = modelMapper.map(order, OrderDTO.class);

        return dto;
    }

    /* convert tu DTO --> Entity*/
    public Orders convertToEntity(OrderDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);

        order.setOderDetailList(this.getOrderDetailById(orderDTO.getOrderDetailIds()));
        order.setUser(this.getUserById(orderDTO.getUser_id()));
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

    private User getUserById(Long id) {
        if (id == null) return null;

        Optional<User> opt = userRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            System.err.println("Không tồn tại user có id là " + id);
            return null;
        }
    }


    private Set<OderDetail> getOrderDetailById(List<Long> ids) {
        Set<OderDetail> set = new HashSet<>();

        if (ids != null) {
            for (long id : ids) {
                Optional<OderDetail> opt = orderDetailRepo.findById(id);

                if (opt.isPresent()) {
                    set.add(opt.get());
                } else {
                    System.err.println("Không tồn tại order detail có id là " + id);
                }
            }
        }

        return set;
    }
    //endregion
}