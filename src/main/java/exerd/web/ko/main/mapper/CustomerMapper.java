package exerd.web.ko.main.mapper;

import exerd.web.ko.main.vo.CustomerVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerMapper {
    public List<CustomerVO> getCustomerList();
}
