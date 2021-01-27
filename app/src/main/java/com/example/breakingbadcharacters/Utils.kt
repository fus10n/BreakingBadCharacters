package com.example.breakingbadcharacters

import kotlin.reflect.KClass

object Utils {

    fun getTag(kClass: KClass<*>): String = kClass.java.simpleName

}