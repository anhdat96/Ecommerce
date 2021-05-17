package com.example.demo.service;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JavaReflectionImpl<T> implements JavaReflection<T> {
    private Class<T> zclass;

    @Override
    public long insert(T o) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
           //
        }catch (Exception e){

        }
        String tableName = "";
        if (zclass.isAnnotationPresent(Table.class)) {
            // check table exist with this class , return the methods annotation for the specified annotation type;
            Table table = zclass.getAnnotation(Table.class);
            // get name of the table
            tableName = table.name();
        }
        String sql = createSQL(zclass, tableName);

        return 0;
    }

    private String createSQL(Class<T> zclass, String tableName) {
        // need name of the table and the entity ( zclass ) to create sql
        StringBuilder fields = new StringBuilder();
        StringBuilder params = new StringBuilder();
        for (Field field : zclass.getDeclaredFields()) {
            // get all field in the specified class then put it in an array
            // can get name of field or value ...
            if (fields.length() > 1) {
                fields.append(",");
                fields.append(",");
            }
            if (field.isAnnotationPresent(Column.class)) {
                Column column = zclass.getAnnotation(Column.class);
                fields.append(column.name());
                params.append("?");
            }
        }
        String sql = " INSERT INTO " + tableName + " (" + fields.toString() + ") VALUES(" + params + ")";
        return sql;
    }
}
