package com.gov.zw.domain;

import java.util.Objects;

public class IdentityName {
    private String name;

    public IdentityName() { }

    public IdentityName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityName that = (IdentityName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
