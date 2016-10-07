package extras;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by C.limbachiya on 10/4/2016.
 */

public class DetailList implements Parcelable {

    String title;
    String image;
    String caption;
    String time;

    protected DetailList(Parcel in) {

        title = in.readString();
        image = in.readString();
        caption = in.readString();
        time = in.readString();

    }

    public static final Creator<DetailList> CREATOR = new Creator<DetailList>() {
        @Override
        public DetailList createFromParcel(Parcel in) {
            return new DetailList(in);
        }

        @Override
        public DetailList[] newArray(int size) {
            return new DetailList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeString(caption);
        parcel.writeString(time);
    }
}
