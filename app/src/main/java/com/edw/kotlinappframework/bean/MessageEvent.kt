package com.edw.kotlinappframework.bean

import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.internal.ParcelableSparseArray
import java.io.Serializable

private const val KEY_INT = "key_int"
private const val KEY_LONG = "key_long"
private const val KEY_FLOAT = "key_float"
private const val KEY_BOOLEAN = "key_boolean"
private const val KEY_DOUBLE = "key_double"
private const val KEY_STRING = "key_string"
private const val KEY_SERIALIZABLE = "key_serializable"
private const val KEY_PARCELABLE = "key_parcelable"



data class MessageEvent(var type: MessageType=MessageType.Text) {

    var bundle = Bundle()

    fun put(value: Int): MessageEvent {
        bundle.putInt(KEY_INT, value)
        return this
    }

    fun put(value: Long): MessageEvent {
        bundle.putLong(KEY_LONG, value)
        return this
    }

    fun put(value: Float): MessageEvent {
        bundle.putFloat(KEY_FLOAT, value)
        return this
    }

    fun put(value: Double): MessageEvent {
        bundle.putDouble(KEY_DOUBLE, value)
        return this
    }

    fun put(value: Boolean): MessageEvent {
        bundle.putBoolean(KEY_BOOLEAN, value)
        return this
    }

    fun put(value: String): MessageEvent {
        bundle.putString(KEY_STRING, value)
        return this
    }

    fun put(value: Serializable): MessageEvent {
        bundle.putSerializable(KEY_SERIALIZABLE, value)
        return this
    }

    fun put(value: Parcelable): MessageEvent {
        bundle.putParcelable(KEY_PARCELABLE, value)
        return this
    }

    fun getInt(): Int {
        return bundle.getInt(KEY_INT)
    }

    fun getLong(): Long {
        return bundle.getLong(KEY_LONG)
    }

    fun getFloat(): Float {
        return bundle.getFloat(KEY_FLOAT)
    }

    fun getDouble(): Double {
        return bundle.getDouble(KEY_DOUBLE)
    }

    fun getBoolean(): Boolean {
        return bundle.getBoolean(KEY_BOOLEAN)
    }

    fun getString(): String {
        return bundle.getString(KEY_STRING)!!
    }

    @Suppress("UNCHECKED_CAST")
    fun<T:Serializable> getSerializable(): Serializable {
        return bundle.getSerializable(KEY_SERIALIZABLE)!! as T
    }


    fun<T:Parcelable> getParcelable(): T? {
        return bundle.getParcelable(KEY_PARCELABLE)!!
    }


    enum class MessageType {
        ShowToast,
        ShowLog,
        ShowSnackBar,
        Text
    }

}
