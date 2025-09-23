package com.negoffer.shortlink.project.common.convention.exception;

import com.negoffer.shortlink.project.common.convention.errorcode.IErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Abstract base class for the three exception types in the project:
 * client-side exceptions, server-side exceptions, and remote service call exceptions.
 *
 * @see com.negoffer.shortlink.project.common.convention.exception.ClientException
 * @see ServiceException
 * @see RemoteException
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null)
                .orElse(errorCode.message());
    }
}
