package com.oqe.satire.over_qualified_exception.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("storeName")
    public String populateStoreName() {
        return "OverQualifiedException";
    }
}
