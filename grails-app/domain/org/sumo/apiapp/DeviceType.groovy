package org.sumo.apiapp

import org.springframework.context.MessageSourceResolvable

enum DeviceType implements MessageSourceResolvable {

    PHONE,
    MOBILE,
    EMAIL,
    WEBSITE,
    FAX

    public Object[] getArguments() {
        [] as Object[]
    }

    String[] getCodes() {
        ["${getClass().name}.${name()}"] as String[]
    }

    public String getDefaultMessage() {
        name()
    }
}