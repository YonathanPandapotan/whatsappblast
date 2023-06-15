package com.usery.whatsappblast.controller;

import com.usery.whatsappblast.model.EmployeeRequestAdvApprove;
import com.usery.whatsappblast.model.EmployeeRequestAdvSubmit;
import com.usery.whatsappblast.model.Template;
import com.usery.whatsappblast.repository.TemplateRepository;
import com.usery.whatsappblast.services.TemplateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class WhatsappController {
    @Autowired
    TemplateRepository templateRepository;
    @Autowired
    TemplateServices templateServices;

    @GetMapping("/")
    public String getHomepage(@RequestParam(name = "name", required = false, defaultValue = "World")String name, Model model){
        model.addAttribute("name", name);
        return "homepage";
    }

    @PostMapping("/template_messaging")
    public String postTemplateMessaging(HttpServletRequest request,
                               HttpServletResponse response, Model model){
        RestTemplate restTemplate=new RestTemplate();

        HttpHeaders header= new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("Authorization", "Bearer EAAR67AAFZAKoBAK6eZBKwZCd0wWZBmqnQZBYgfv4f5EnL8cPeYU4Dct0gmNbxpEhZBazj9c2aOlVYjIRDHaSPZC6MWsihZBnARwsZAg342ITpc14tpBc1xRq91LbF1R2WbdLxqODQHFgwoaxPE4zCItEE9em07S2DB8ZB1grYFaFbVMWYBWmsFMMXqls08YEPuZAY8ZB5n65Kj2TPct0HrMEZBOPW");
        header.add("Accept", "application/json");

        Map<String, Object> data = new LinkedHashMap<>();

        if(request.getParameter("templatename").equals("employee_request_adv_submit")){
            EmployeeRequestAdvSubmit employeeRequestAdvSubmit = new EmployeeRequestAdvSubmit();
            employeeRequestAdvSubmit.setRequestNo(request.getParameter("requestno"));
            employeeRequestAdvSubmit.setRequestDate(Date.valueOf(request.getParameter("date")));
            employeeRequestAdvSubmit.setRequesterName(request.getParameter("requestername"));
            employeeRequestAdvSubmit.setAmount(Double.parseDouble(request.getParameter("advanceamount")));
            data.put("template", employeeRequestAdvSubmit.compileWhatsapp("6281290709542"));
        }else{
            EmployeeRequestAdvApprove employeeRequestAdvApprove = new EmployeeRequestAdvApprove();
            employeeRequestAdvApprove.setRequestNo(request.getParameter("requestno"));
            employeeRequestAdvApprove.setTanggal(Date.valueOf(request.getParameter("date")));
            employeeRequestAdvApprove.setApproverName(request.getParameter("approovername"));
            employeeRequestAdvApprove.setNominal(Double.parseDouble(request.getParameter("advanceamount")));
            data.put("template", employeeRequestAdvApprove.compileWhatsapp("6281290709542"));
        }

        HttpEntity<Map<String, Object>> requestHttp =new HttpEntity<Map<String, Object>>(data, header);

        ResponseEntity<?> result =
                restTemplate.exchange("https://graph.facebook.com/v16.0/105406685674131/messages", HttpMethod.POST, requestHttp, String.class);
        Object object = result.getBody();

        model.addAttribute("name", "test");
        model.addAttribute("phoneid", request.getParameter("phoneid"));
        model.addAttribute("templatename", request.getParameter("templatename"));
        model.addAttribute("employeename", request.getParameter("employeename"));
        model.addAttribute("date", request.getParameter("date"));
        model.addAttribute("requestno", request.getParameter("requestno"));
        model.addAttribute("advanceamount", request.getParameter("advanceamount"));
        model.addAttribute("requestername", request.getParameter("requestername"));
        return "homepage";
    }

    @GetMapping("/template_messaging")
    public String getTemplateMessaging(@RequestParam(name="name", required = false, defaultValue = "World")String name, Model model){
        model.addAttribute("name", name);
        return "template_messaging";
    }

    @GetMapping("/template_list")
    public String getTemplateList(@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        model.addAttribute("name", name);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);
        currentPage = currentPage-1;

        Page<Template> pertanyaanPage = templateServices.getHalaman(currentPage, pageSize);
        List<Template> data = pertanyaanPage.getContent();

        int totalPages = pertanyaanPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("list_halaman", pageNumbers);
        }

//        model.addAttribute("title", "Daftar Pertanyaan");
        model.addAttribute("pages", pertanyaanPage);
        model.addAttribute("total_page", pertanyaanPage.getTotalPages());
        model.addAttribute("pages_data", data);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);

        return "template_list";
    }

    @GetMapping("/template_detail")
    public String getTemplateDetail(@RequestParam(name="name", required = false, defaultValue = "World")String name, Model model){
        model.addAttribute("name", name);
        return "template_detail";
    }

    @GetMapping("/template_form")
    public String getTemplateForm(@RequestParam(name="name", required = false, defaultValue = "World")String name, Model model){
        model.addAttribute("template", new Template());
        return "template_form";
    }

    @PostMapping("/template_form")
    public String getTemplatePost(@ModelAttribute Template template, Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        templateRepository.save(template);
        model.addAttribute("template",template);
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(2);

        Page<Template> pertanyaanPage = templateServices.getHalaman(currentPage, pageSize);
        List<Template> data = pertanyaanPage.getContent();

        int totalPages = pertanyaanPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("list_halaman", pageNumbers);
        }

//        model.addAttribute("title", "Daftar Pertanyaan");
        model.addAttribute("pages", pertanyaanPage);
        model.addAttribute("total_page", pertanyaanPage.getTotalPages());
        model.addAttribute("pages_data", data);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);

        return "template_list";
    }
}
