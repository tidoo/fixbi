package com.arsi.fixbi

import com.arsi.fixbi.adapters.BindingRecyclerAdapter
import com.arsi.fixbi.model.BatmanModel
import junit.framework.Assert.assertEquals
import org.junit.Test

class AdapterTest {



    @Test
    fun adapter_size(){
        val data = arrayListOf(
            BatmanModel(0,"0"),
            BatmanModel(1,"2"),
        )

        val adapter = BindingRecyclerAdapter("",1,data)

        assertEquals("BindingRecyclerAdapter is not the correct size", data.size, adapter.itemCount)

    }








}