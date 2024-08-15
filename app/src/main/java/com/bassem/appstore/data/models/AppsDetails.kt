package com.bassem.appstore.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("apps")
data class AppsDetails(
    @PrimaryKey(autoGenerate = true)
    val keyId:Int,
    val added: String?="",
    val downloads: Int?=0,
    val graphic: String?="",
    val icon: String?="",
    val id: Int?=0,
    val md5sum: String?="",
    val modified: String?="",
    val name: String?="",
    val `package`: String?="",
    val pdownloads: Int?=0,
    val rating: Double?=0.0,
    val size: Int?=0,
    val store_id: Int?=0,
    val store_name: String?="",
    val updated: String?="",
    val uptype: String?="",
    val vercode: Int?=0,
    val vername: String?="",
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt()?:0,
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(added)
        parcel.writeInt(downloads ?: -1)
        parcel.writeString(graphic)
        parcel.writeString(icon)
        parcel.writeInt(id ?: -1)
        parcel.writeString(md5sum)
        parcel.writeString(modified)
        parcel.writeString(name)
        parcel.writeString(`package`)
        parcel.writeInt(pdownloads ?: -1)
        parcel.writeDouble(rating ?: -1.0)
        parcel.writeInt(size ?: -1)
        parcel.writeInt(store_id ?: -1)
        parcel.writeString(store_name)
        parcel.writeString(updated)
        parcel.writeString(uptype)
        parcel.writeInt(vercode ?: -1)
        parcel.writeString(vername)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppsDetails> {
        override fun createFromParcel(parcel: Parcel): AppsDetails {
            return AppsDetails(parcel)
        }

        override fun newArray(size: Int): Array<AppsDetails?> {
            return arrayOfNulls(size)
        }
    }
}
