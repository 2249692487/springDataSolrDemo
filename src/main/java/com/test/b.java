package com.test;


import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/2/17
 */
public interface b<T> extends c<T> {
    int getTotalPages();

    long getTotalElements();

    <S> Page<S> map(Converter<? super T, ? extends S> var1);
}
