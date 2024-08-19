package org.thewhitemage13;

import java.io.Serializable;

public class UserEvent implements Serializable {
    private Long userId;
    private String username;
    private String phoneNumber;
    private String region;
    private String email;
    private String firstName;
    private String surname;
    private String lastName;
    private String profilePictureUrl;

    public UserEvent() {
    }

    public UserEvent(Long userId, String username, String phoneNumber, String region, String email, String firstName, String surname, String lastName, String profilePictureUrl) {
        this.userId = userId;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.region = region;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.profilePictureUrl = profilePictureUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", region='" + region + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                '}';
    }
}
