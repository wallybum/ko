package exerd.web.ko.main.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import exerd.web.ko.main.service.CustomerService;
import exerd.web.ko.main.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value="/")
    public String main(Model model) {
        Gson gson = new Gson();

        List<CustomerVO> customerTotalList = new ArrayList<>();
        customerTotalList = customerService.getCustomerList();

        int customerListCount = 0;

        if(customerTotalList.size() > 0){
            customerListCount = customerTotalList.size() / 15;
            if((customerListCount % 15) > 0){
                customerListCount += 1;
            }
        }

        JsonElement element = gson.toJsonTree(customerTotalList, new TypeToken<List<CustomerVO>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();

        model.addAttribute("customerList", jsonArray);
        model.addAttribute("customerListCount", customerListCount);

        return "index.html";
    }
}
