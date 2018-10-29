package com.trainings.json.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Seda
 */
public class RestResource {
    private List<Persons> content = new ArrayList<>();

    public RestResource(){}

    public RestResource(List<Persons> content) {
        this.content = content;
    }

    public List<Persons> getContent() {
        return content;
    }

    public void setContent(List<Persons> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RestResource{" +
                "content=" + content +
                '}';
    }
}
