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
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    @Column(name = "walletName")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "value")
    private double value;
    @Column(name = "valute")
    private char valute;

    public Wallet(User user, String name, String description, double value, char valute) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.value = value;
        this.valute = valute;
    }

    public Wallet() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public char getValute() {
        return valute;
    }

    public void setValute(char valute) {
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
