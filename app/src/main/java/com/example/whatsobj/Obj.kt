package com.example.whatsobj

import java.util.*
import kotlin.collections.ArrayList

public class Obj(newObj: String){

    var name : String = newObj

    companion object {
        val list: MutableList<Obj> = ArrayList()
    }
    init {
        list.add(Obj(name))
    }



}

