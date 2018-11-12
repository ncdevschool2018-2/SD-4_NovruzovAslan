package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private User user;
    @Column(name = "wallet_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "value")
    private Double value;
    @Column(name = "valute")
    private Character valute;

    public Wallet(User user, String name, String description, Double value, Character valute) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.value = value;
        this.valute = valute;
    }

    public Wallet() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Character getValute() {
        return valute;
    }

    public void setValute(Character valute) {
        this.valute = valute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wallet)) return false;
        Wallet wallet = (Wallet) o;
        return id == wallet.id &&
                Double.compare(wallet.value, value) == 0 &&
                valute == wallet.valute &&
                Objects.equals(user, wallet.user) &&
                Objects.equals(name, wallet.name) &&
                Objects.equals(description, wallet.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, description, value, valute);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", valute=" + valute +
                '}';
    }

}
