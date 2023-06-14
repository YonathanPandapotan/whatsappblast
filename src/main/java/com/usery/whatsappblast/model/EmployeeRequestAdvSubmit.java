package com.usery.whatsappblast.model;

import java.util.*;

public class EmployeeRequestAdvSubmit {
    private static final String templateName = "employee_request_adv_approve";
    private String employeeName;
    private Date requestDate;
    private String requestNo;
    private double amount;
    private String requesterName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public Map<String, Object> compileWhatsapp(String phoneNumber){
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
        parameter.put("text", this.employeeName);
        parameters.add(parameter);

        parameter = new LinkedHashMap<>();
        parameter.put("type", "text");
        parameter.put("text", this.requestDate);
        parameters.add(parameter);

        parameter = new LinkedHashMap<>();
        parameter.put("type", "text");
        parameter.put("text", this.requestNo);
        parameters.add(parameter);

        parameter = new LinkedHashMap<>();
        parameter.put("type", "text");
        parameter.put("text", "Rp. " + String.format("%,.2f", this.amount));
        parameters.add(parameter);

        parameter = new LinkedHashMap<>();
        parameter.put("type", "text");
        parameter.put("text", this.requesterName);
        parameters.add(parameter);

        bodyComponents.put("parameters", parameters);
        components.add(bodyComponents);
        template.put("components", components);

        data.put("template", template);
        return data;
    }
}
