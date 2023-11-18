package wiki.sponge.dynamicdatasource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_order")
public class Order {
	
	@TableId(type=IdType.ASSIGN_ID)
    private Long id;

}