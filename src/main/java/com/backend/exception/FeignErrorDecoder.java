package com.backend.exception;

import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();


    @Override
    public Exception decode(String methodKey, Response response) {
        final HttpStatus httpStatus = HttpStatus.resolve(response.status());
        System.out.println("httpStatus : {}, request: {}" + httpStatus + response.request().toString());

        // 429 too many request 300ms 이후 재시도
        if (httpStatus == HttpStatus.TOO_MANY_REQUESTS) {
            return new RetryableException(
                    response.status(),
                    "retry!!! (too many request)",
                    response.request().httpMethod(),
                    DateUtils.addMilliseconds(new Date(), 300),
                    response.request()
            );
        }
        return errorDecoder.decode(methodKey, response);
    }

    @Bean
    public Retryer retryer(){
        return new Retryer.Default();
    }
}