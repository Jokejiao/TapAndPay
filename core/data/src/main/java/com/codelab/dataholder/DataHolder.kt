package com.codelab.dataholder

/**
 * A simulated data layer for passing data from one module to another
 */
class DataHolder {
    private var data = ""

    fun setData(data: String) {
        if (data.isNotEmpty()) this.data = data
    }

    fun getData() = data
}