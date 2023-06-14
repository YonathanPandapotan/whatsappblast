package com.usery.whatsappblast.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(TemplateDetailKey.class)
public class TemplateDetail {
    @NotNull
    @Id
    private String templateId;
    @NotNull
    @Id
    private String templateDetailId;

    @NotNull
    private String textName;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateDetailId() {
        return templateDetailId;
    }

    public void setTemplateDetailId(String templateDetailId) {
        this.templateDetailId = templateDetailId;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }
}
