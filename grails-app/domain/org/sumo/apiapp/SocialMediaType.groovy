package org.sumo.apiapp

enum SocialMediaType {

    FACEBOOK("facebook"),
    TWITTER("twitter"),
    INSTAGRAM("instagram"),
    YOUTUBE("YouTube")

    final String value

    SocialMediaType(String value) {
        this.value = value
    }

    String toString() {
        value
    }

    String getName() {
        name()
    }
}