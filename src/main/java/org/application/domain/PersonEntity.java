package org.application.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity(name="Person")
@Table(name="\"PERSON\"")
public class PersonEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(length = 50, name="\"name\"")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
