package id.putraprima.skorbola;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Challenge implements Parcelable {
    private String hometext, awaytext;
    private Uri homeUri;

    public Uri getHomeUri() {
        return homeUri;
    }

    public void setHomeUri(Uri homeUri) {
        this.homeUri = homeUri;
    }

    public Uri getAwayUri() {
        return awayUri;
    }

    public void setAwayUri(Uri awayUri) {
        this.awayUri = awayUri;
    }

    public static Creator<Challenge> getCREATOR() {
        return CREATOR;
    }

    private Uri awayUri;

    public Challenge(String hometext, String awaytext, Uri homeUri, Uri awayUri) {
        this.hometext = hometext;
        this.awaytext = awaytext;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
    }

    protected Challenge(Parcel in) {
        this.hometext = in.readString();
        this.awaytext = in.readString();
        this.homeUri = in.readParcelable(Uri.class.getClassLoader());
        this.awayUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Challenge> CREATOR = new Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(Parcel in) {
            return new Challenge(in);
        }

        @Override
        public Challenge[] newArray(int size) {
            return new Challenge[size];
        }
    };

    public String getHometext() {
        return hometext;
    }

    public void setHometext(String hometext) {
        this.hometext = hometext;
    }

    public String getAwaytext() {
        return awaytext;
    }

    public void setAwaytext(String awaytext) {
        this.awaytext = awaytext;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hometext);
        dest.writeString(this.awaytext);
        dest.writeParcelable(this.homeUri, flags);
        dest.writeParcelable(this.awayUri, flags);

    }
}
