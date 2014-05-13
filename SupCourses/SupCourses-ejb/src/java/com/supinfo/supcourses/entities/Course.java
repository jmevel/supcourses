package com.supinfo.supcourses.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.*;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private Level levelDifficulty;
    
    @Column
    @Enumerated(EnumType.STRING)
    private Subject subject;
    
    @Column
    private int duration;
    
    @Column
    private String description;
    
    @Column 
    private boolean isTaken;
    
    private Collection<User> users;
    
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name="QUIZZ_ID")
    @JoinTable(name="course_quizz", joinColumns = @JoinColumn(name="COURSE_ID"), inverseJoinColumns = @JoinColumn(name="QUIZZ_ID"))
    private Quizz quizz;

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }

    
    public Quizz getQuizz() {
        return quizz;
    }

    @ManyToMany(mappedBy = "course", cascade=CascadeType.ALL)
    /*@JoinTable(name = "User_Course", joinColumns = {
        @JoinColumn(name = "Course_ID", referencedColumnName = "id")
        }, inverseJoinColumns = {@JoinColumn(name = "User_ID", referencedColumnName = "id")})  */
    public Collection<User> getUsers() {
        return users;
    }
    
        public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public void setIsTaken(boolean isTaken) {
        this.isTaken = isTaken;
    }

    public boolean isIsTaken() {
        return isTaken;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevelDifficulty(Level levelDifficulty) {
        this.levelDifficulty = levelDifficulty;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public Level getLevelDifficulty() {
        return levelDifficulty;
    }

    public Subject getSubject() {
        return subject;
    }

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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supinfo.supcourses.entities.Course[ id=" + id + " ]";
    }
    
}
