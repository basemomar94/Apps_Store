package com.bassem.appstore.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Entity("apps")
data class AppsDetails(
    val added: String?,
    val apk_tags: List<String>?,
    val downloads: Int?,
    val graphic: String?,
    val icon: String?,
    val id: Int?,
    val md5sum: String?,
    val modified: String?,
    val name: String?,
    val `package`: String?,
    val pdownloads: Int?,
    val rating: Double?,
    val size: Int?,
    val store_id: Int?,
    val store_name: String?,
    val updated: String?,
    val uptype: String?,
    val vercode: Int?,
    val vername: String?,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
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
        parcel.writeStringList(apk_tags)
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
