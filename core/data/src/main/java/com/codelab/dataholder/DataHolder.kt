package com.codelab.dataholder

class DataHolder {
    private var data = ""

    public fun setData(data: String) {
        if (data.isNotEmpty()) this.data = data
    }

    public fun getData() = data
}