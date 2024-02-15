package com.gov.zw.json;

import java.util.Objects;

public class IdentityNameJson {
    private String name;

    IdentityNameJson() { }

    public IdentityNameJson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "IdentityNameJson{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityNameJson that = (IdentityNameJson) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
