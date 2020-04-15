package com.xlcxx.config.auth.CodeFilter.utils;

public interface ValidateCodeGenerator {

    ValidateCode createCodeImage();

    ValidateCode createCodeSms();
}
