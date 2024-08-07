package com.example.gerenciadortarefas.comum.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotationsInside
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
public @interface DateTimePattern {
}
