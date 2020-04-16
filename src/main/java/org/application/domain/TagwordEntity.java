package org.application.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity(name="Tagword")
@Table(name="\"TAGWORD\"")
public class TagwordEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(length = 50, name="\"term\"")
    private String term;

    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
