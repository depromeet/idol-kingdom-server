package com.example.idolkingdom.util

enum class IdolImage(private val idolName: String) {
    NCT("NCT"),
    NCT127("NCT127"),
    NCTDREAM("NCTDREAM"),
    NCTU("NCTU"),
    DALSHABET("달샤벳");

    companion object {
//        fun haveIdol(name: String): Boolean {
//            var isHaveIdol = false
//            values().filter { idolImage -> idolImage.idolName == name }
//                .firstOrNull()
//
//        }

        fun getIdolGroupByName(name: String): IdolImage? = values().firstOrNull { idolImage -> idolImage.idolName == name }

    }


}