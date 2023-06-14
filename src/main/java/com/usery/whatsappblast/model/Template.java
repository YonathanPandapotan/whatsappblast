package com.usery.whatsappblast.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Template {
    @NotNull
    @Id
    @GenericGenerator(name = "template_id", strategy = "com.usery.whatsappblast.generator.TemplateGenerator")
    @GeneratedValue(generator = "template_id")
    private String templateId;
    @NotNull
    private String templateIdentifier;
    @NotNull
    private String templateName;
    private String templateContext;

    public String getTemplateid() {
        return templateId;
    }

    public void setTemplateid(String templateid) {
        this.templateId = templateid;
    }

    public String getTemplateIdentifier() {
        return templateIdentifier;
    }

    public void setTemplateIdentifier(String templateIdentifier) {
        this.templateIdentifier = templateIdentifier;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContext() {
        return templateContext;
    }

    public void setTemplateContext(String templateContext) {
        this.templateContext = templateContext;
    }
}
