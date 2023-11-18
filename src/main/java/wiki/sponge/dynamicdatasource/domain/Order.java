package wiki.sponge.dynamicdatasource.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Order {
	
	@TableId(type=IdType.ASSIGN_ID)
    private Long id;

}