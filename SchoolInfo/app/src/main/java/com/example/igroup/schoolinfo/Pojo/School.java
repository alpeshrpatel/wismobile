package com.example.igroup.schoolinfo.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iGroup on 9/5/2017.
 */


public class School implements Parcelable {

    String _id;
    String name;
    String address;

    protected School(Parcel in) {
        _id = in.readString();
        name = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        zipcode = in.readString();
        type = in.readString();
        website = in.readString();
        phone = in.createStringArray();
        email = in.createStringArray();
        boys = in.readInt();
        girls = in.readInt();
        maleteacher = in.readInt();
        femaleteacher = in.readInt();
        starttime = in.readInt();
        endtime = in.readInt();
        _v = in.readInt();
        daycare = in.readByte() != 0;
        kg = in.readByte() != 0;
        primary = in.readByte() != 0;
        high = in.readByte() != 0;
    }

    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String[] getPhone() {
        return phone;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public int getBoys() {
        return boys;
    }

    public void setBoys(int boys) {
        this.boys = boys;
    }

    public int getGirls() {
        return girls;
    }

    public void setGirls(int girls) {
        this.girls = girls;
    }

    public int getMaleteacher() {
        return maleteacher;
    }

    public void setMaleteacher(int maleteacher) {
        this.maleteacher = maleteacher;
    }

    public int getFemaleteacher() {
        return femaleteacher;
    }

    public void setFemaleteacher(int femaleteacher) {
        this.femaleteacher = femaleteacher;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int get_v() {
        return _v;
    }

    public void set_v(int _v) {
        this._v = _v;
    }

    public boolean isDaycare() {
        return daycare;
    }

    public void setDaycare(boolean daycare) {
        this.daycare = daycare;
    }

    public boolean isKg() {
        return kg;
    }

    public void setKg(boolean kg) {
        this.kg = kg;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isHigh() {
        return high;
    }

    public void setHigh(boolean high) {
        this.high = high;
    }

    String city;
    String state;
    String country;
    String zipcode;
    String type;
    String website;
    String[] phone,email;
    int boys,girls,maleteacher,femaleteacher,starttime,endtime,_v;
    boolean daycare,kg,primary,high;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(state);
        parcel.writeString(country);
        parcel.writeString(zipcode);
        parcel.writeString(type);
        parcel.writeString(website);
        parcel.writeStringArray(phone);
        parcel.writeStringArray(email);
        parcel.writeInt(boys);
        parcel.writeInt(girls);
        parcel.writeInt(maleteacher);
        parcel.writeInt(femaleteacher);
        parcel.writeInt(starttime);
        parcel.writeInt(endtime);
        parcel.writeInt(_v);
        parcel.writeByte((byte) (daycare ? 1 : 0));
        parcel.writeByte((byte) (kg ? 1 : 0));
        parcel.writeByte((byte) (primary ? 1 : 0));
        parcel.writeByte((byte) (high ? 1 : 0));
    }
}
