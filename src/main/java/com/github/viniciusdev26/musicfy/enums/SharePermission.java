package com.github.viniciusdev26.musicfy.enums;

public enum SharePermission {
    VIEW(0),
    EDIT(1);

    private final int value;

    SharePermission(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SharePermission fromValue(int value) {
        for (SharePermission permission : SharePermission.values()) {
            if (permission.value == value) {
                return permission;
            }
        }
        throw new IllegalArgumentException("Invalid SharePermission value: " + value);
    }
}
