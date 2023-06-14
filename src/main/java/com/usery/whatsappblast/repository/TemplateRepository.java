package com.usery.whatsappblast.repository;

import com.usery.whatsappblast.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, String> {

    @Override
    <S extends Template> S save(S entity);
}
