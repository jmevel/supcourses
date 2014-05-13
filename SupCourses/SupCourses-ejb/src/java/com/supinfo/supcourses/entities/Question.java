package com.supinfo.supcourses.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String description;
    
    /*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="QUIZZ_ID")*/
    @ManyToOne
    private Quizz quizz;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDescription() {
        return description;
    }

    public Collection<String> getAnswers() {
        return answers;
    }

    public int getGoodAnswerIndex() {
        return goodAnswerIndex;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswers(Collection<String> answers) {
        this.answers = answers;
    }

    public void setGoodAnswerIndex(int goodAnswerIndex) {
        this.goodAnswerIndex = goodAnswerIndex;
    }
    
    private Collection<String> answers;
    
    private int goodAnswerIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supinfo.supcourses.entities.Question[ id=" + id + " ]";
    }
    
}
