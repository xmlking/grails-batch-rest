package org.sumo.apiapp

enum ProviderType {

    PHYSICIAN("physician"),
    DENTIST("dentist"),
    NURSE("nurse"),
    HOSPITAL("hospital"),
    CLINIC("clinic"),
    PHARMACY("pharmacy")

    final String value

    ProviderType(String value) {
        this.value = value
    }

    String toString() {
        value
    }

    String getName() {
        name()
    }
}