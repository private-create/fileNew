package com.test.fileupload.inteface;

import com.test.fileupload.User;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class FileImport implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{User.class.getName()};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }
}
