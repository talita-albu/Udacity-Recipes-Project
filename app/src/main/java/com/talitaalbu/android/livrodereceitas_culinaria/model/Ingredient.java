package com.talitaalbu.android.livrodereceitas_culinaria.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {

     String quantity;
     String measure;
     String ingredient;

    protected Ingredient(Parcel in) {
        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    public String getQuantity() {
        return quantity;
    }

    public String getType() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "quantity='" + quantity + '\'' +
                ", type='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}
