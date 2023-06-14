package com.usery.whatsappblast.services;

import com.usery.whatsappblast.model.Template;
import com.usery.whatsappblast.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemplateServices {
    @Autowired
    TemplateRepository templateRepository;

    public Page<Template> getHalaman(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return templateRepository.findAll(pageable);
    }
}
