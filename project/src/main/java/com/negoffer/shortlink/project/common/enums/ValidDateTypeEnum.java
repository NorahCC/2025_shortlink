package com.negoffer.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Validity type
 */
@RequiredArgsConstructor
public enum ValidDateTypeEnum {

    /**
     * Permanent validity
     */
    PERMANENT(0),

    /**
     * Custom validity
     */
    CUSTOM(1);

    @Getter
    private final int type;
}
