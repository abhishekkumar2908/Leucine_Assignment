package com.leucine.Assignment.service;

import com.leucine.Assignment.dao.Classes;
import com.leucine.Assignment.enums.ClassName;
import com.leucine.Assignment.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassNameService {
    @Autowired
    private ClassRepository classRepository;

    public void saveClass(ClassName className, Long studentId){
         Classes classes = Classes.builder()
                .className(className)
                .studentId(studentId)
                .build();
    }
}
