package com.nw.filters;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * @Author liuyefeng
 * @Date 2018/12/13 12:25
 */
public class ClassTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        return !(classMetadata.isAbstract() || classMetadata.isInterface() || classMetadata.isAnnotation());
    }
}
