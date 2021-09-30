
package Model;

import java.io.Serializable;

public class Student implements Serializable {

    private String masv, name, phone, address;
    private boolean gender;

    public String getMasv() {
        return masv;
    }
     public void setMasv(String masv) {
        this.masv = masv;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Student() {
    }
    public Student(String masv, String name, String phone, String address, boolean gender) {
        this.masv = masv;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean isGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }
}

