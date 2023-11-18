package wiki.sponge.dynamicdatasource.mapper;

import java.util.List;
import wiki.sponge.dynamicdatasource.domain.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    List<Order> selectAll();
}