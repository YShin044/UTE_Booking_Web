package com.example.doancuoiki.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id") // Đảm bảo tên cột trong cơ sở dữ liệu là 'id'
    private int id;

    @Column(name="username", columnDefinition = "nvarchar(100) NOT NULL")
    private String username;

    @Column(name="password", columnDefinition = "nvarchar(100) NOT NULL")
    private String password;

    @Column(name="email", columnDefinition = "nvarchar(200) NULL")
    private String email;

    @Column(name="phone", columnDefinition = "nvarchar(15) NULL")
    private String phone;

    @Column(name="status")
    private int status;

    // Bi-directional one-to-many association to Booking
    @OneToMany(mappedBy="user")
    private List<Booking> bookings;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    // Helper methods for managing bi-directional relationship
    public Booking addBooking(Booking booking) {
        getBookings().add(booking);
        booking.setUser(this);
        return booking;
    }

    public Booking removeBooking(Booking booking) {
        getBookings().remove(booking);
        booking.setUser(null);
        return booking;
    }
}
