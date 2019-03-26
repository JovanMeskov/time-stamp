package com.example.demo.domain;

import java.util.Objects;

public class Timestamp {

    private Long unix;
    private String naturalLanguage;

    public Timestamp(Long unix, String naturalLanguage) {
        this.unix = unix;
        this.naturalLanguage = naturalLanguage;
    }

    private Long getUnix() {
        return unix;
    }

    private String getNaturalLanguage() {
        return naturalLanguage;
    }

    @Override
    public boolean equals(Object obj) {
        return naturalLanguage == null && null == ((Timestamp) obj).naturalLanguage &&
                Objects.equals(unix, ((Timestamp) obj).unix) || obj instanceof Timestamp &&
                this.naturalLanguage.equals(((Timestamp) obj).getNaturalLanguage()) &&
                this.unix.equals(((Timestamp) obj).getUnix());
    }
}
