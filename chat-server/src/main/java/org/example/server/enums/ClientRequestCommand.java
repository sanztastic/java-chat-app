package org.example.server.enums;

public enum ClientRequestCommand {
    ADD_USER("username"),
    GROUP_CHAT("group"),
    PERSONAl_CHAT("personal");
    ClientRequestCommand(String username) {

    }
}
