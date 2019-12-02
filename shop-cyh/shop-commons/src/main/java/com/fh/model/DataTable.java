package com.fh.model;

import java.io.Serializable;

public class DataTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer drew;
    private Integer start;
    private Integer length;

    public Integer getDrew() {
        return drew;
    }

    public void setDrew(Integer drew) {
        this.drew = drew;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    //private static final long serialVersionUID = 1160815433274576788L;

}
