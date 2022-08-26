package com.example.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public  class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idUser;
    private String userName;
    private String password;
    private String cin;
    private String tel;
    private String email;
    private String lastName;
    private String firstName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String civilState;
    private String adresse;
    private int postalcode;
    private String studyLevel;
    private String job;
    private int monthlyUncome;
    private int nbrOffamilly;



    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)

    @ToString.Exclude
  //  @JsonIgnore
    private List<Post> posts;

    public User(String userName,String firstName, String lastName, String email,String cin,String adresse,Date dateOfBirth,
                String civilState ,
                int postalcode,
                String studyLevel,
                String job,
                int monthlyUncome,

               int nbrOffamilly,
                String tel
            ,String password,Role role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cin= cin;
        this.adresse=adresse;
        this.dateOfBirth=dateOfBirth;
        this.civilState=civilState;
        this.postalcode=postalcode;
        this.studyLevel=studyLevel;
        this.job=job;
        this.monthlyUncome=monthlyUncome;
        this.nbrOffamilly=nbrOffamilly;
        this.tel=tel;
        this.password = password;
        this.role = role;

    }




    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCivilState() {
        return civilState;
    }

    public void setCivilState(String civilState) {
        this.civilState = civilState;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMonthlyUncome() {
        return monthlyUncome;
    }

    public void setMonthlyUncome(int monthlyUncome) {
        this.monthlyUncome = monthlyUncome;
    }

    public int getNbrOffamilly() {
        return nbrOffamilly;
    }

    public void setNbrOffamilly(int nbrOffamilly) {
        this.nbrOffamilly = nbrOffamilly;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }




    @JsonManagedReference
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
