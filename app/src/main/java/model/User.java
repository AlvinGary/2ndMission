package model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String nama, address;
    private int age;

    public User(){
        this.nama="";
        this.age=0;
        this.address="";
    }
    public User(String nama, int age, String address){
        this.nama=nama;
        this.age=age;
        this.address=address;
    }

    protected User(Parcel in) {
        nama = in.readString();
        address = in.readString();
        age = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(address);
        dest.writeInt(age);
    }
}
