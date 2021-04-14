package exerd.web.ko.main.service;

import exerd.web.ko.main.mapper.CustomerMapper;
import exerd.web.ko.main.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerVO> getCustomerList(){
        return customerMapper.getCustomerList();
    }
}
