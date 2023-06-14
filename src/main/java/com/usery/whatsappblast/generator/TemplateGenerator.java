package com.usery.whatsappblast.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TemplateGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "USER";
        String suffix = "";
        try {
            Connection connection = session.connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(template_id) template_id from template");
            if(resultSet.next()) {
                String temp = resultSet.getString("template_id");
                if(temp==null || temp.isEmpty()){
                    suffix="001";
                }else{
                    Integer id = Integer.parseInt(temp.substring(prefix.length())) + 1;
                    suffix = String.format("%03d", id);
                }
            }else{
                suffix="001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefix + suffix;
    }
}
