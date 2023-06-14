package com.usery.whatsappblast.model;

import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeRequestAdvApprove {

    private static final String templateName = "employee_request_adv_approve";
    private Date requestDate;
    private String requestNo;
    private Double nominal;
    private String approverName;

    public Date getTanggal() {
        return requestDate;
    }

    public void setTanggal(Date tanggal) {
        this.requestDate = tanggal;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public Map<String, Object> compileWhatsapp(String phoneNumber){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("messaging_product", "whatsapp");
        data.put("to", phoneNumber);
        data.put("type", "template");

        Map<String, Object> template = new LinkedHashMap<>();
        template.put("name", this.templateName);

        Map<String, Object> language = new LinkedHashMap<>();
        language.put("code", "en");

        template.put("language", language);

        List<Map<String, Object>> components = new ArrayList<>();
        Map bodyComponents = new LinkedHashMap();
        bodyComponents.put("type", "body");

        List<Map<String, Object>> parameters = new ArrayList<>();

        Map<String, Object> parameter = new LinkedHashMap<>();
        parameter.put("type", "text");
        parameter.put("text", formatter.format(this.requestDate));
        parameters.add(parameter);

        parameter = new LinkedHashMap<>();
        parameter.put("type", "text");
        parameter.put("text", this.requestNo);
        parameters.add(parameter);

        parameter = new LinkedHashMap<>();
        parameter.put("type", "text");
        parameter.put("text", "Rp. " + String.format("%,.2f", this.nominal));
        parameters.add(parameter);

        parameter = new LinkedHashMap<>();
        parameter.put("type", "text");
        parameter.put("text", this.approverName);
        parameters.add(parameter);

        bodyComponents.put("parameters", parameters);
        components.add(bodyComponents);
        template.put("components", components);

        data.put("template", template);

        return data;
    }
}
