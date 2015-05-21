package org.sumo.apiapp

enum GenderType {
    MALE('male'), FEMALE('female')

    final String value

    GenderType(String value) {
        this.value = value
    }

    String toString() {
        value
    }

    String getName() {
        name()
    }
}