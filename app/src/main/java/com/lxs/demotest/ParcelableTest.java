package com.lxs.demotest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 实现功能：
 * Created by lvxinsheng on 2019/2/23 上午10:49
 */
public class ParcelableTest implements Parcelable {
    public int a;
    public String b;
    public float c;

    public ParcelableTest() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.a);
        dest.writeString(this.b);
        dest.writeFloat(this.c);
    }

    protected ParcelableTest(Parcel in) {
        this.a = in.readInt();
        this.b = in.readString();
        this.c = in.readFloat();
    }

    public static final Creator<ParcelableTest> CREATOR = new Creator<ParcelableTest>() {
        @Override
        public ParcelableTest createFromParcel(Parcel source) {
            return new ParcelableTest(source);
        }

        @Override
        public ParcelableTest[] newArray(int size) {
            return new ParcelableTest[size];
        }
    };
}
