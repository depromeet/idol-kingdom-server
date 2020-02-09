package com.example.idolkingdom.util

enum class IdolImage(private val idolName: String) {
    NCT("NCT"),
    NCT127("NCT127"),
    NCTDREAM("NCTDREAM"),
    NCTU("NCTU"),
    DALSHABET("달샤벳"),
    DASONI("다소니"),
    DAVICHI("다비치"),
    DREAMCATCHER("드림캐쳐"),
    GIRLSDAY("걸스데이"),
    GOLDENCHILD("골든 차일드"),
    GOODDAY("굿데이"),
    GOT7("GOT7"),
    GREYISH("그레이시"),
    GUGUDAN("구구단"),
    GWSN("공원소녀"),
    LABOUM("라붐"),
    LOVELYZ("러블리즈"),
    MAMAMOO("마마무"),
    MELOMANCE("멜로망스"),
    MOBB("MOBB"),
    MOMOLAND("모모랜드"),
    MONSTAX("몬스타엑스"),
    MUSKY("머스키"),
    MYTEEN("마이틴"),
    NASTYNASTY("네스티네스티"),
    NATURE("네이처"),
    NEOPUNCH("네온펀치"),
    NINEMUSES("나인뮤지"),
    NUEST("뉴이스트"),
    RAINBOW("레인보우"),
    REDVELVET("레드벨벳"),
    RHYTHMPOWER("리듬파워"),
    ROCKETPUNCH("로켓펀치"),
    ROCOBERRY("로코베리"),
    THESEEYA("더씨야"),
    TWICE("트와이스"),
    THEGRACE("천상지희"),
    KNOCK("크나큰"),
    AM2("2am"),
    PM2("2pm"),
    MINUTE4("포미닛"),
    CARD("카드"),
    CHERRYBULLET("체리블렛"),
    CLC("씨엘씨"),
    COCOSORI("코코소리"),
    FIESTA("피에스타"),
    FINKL("핑클"),
    FROMIS9("프로미스나인"),
    GOD("지오디"),
    JBJ("JBJ"),
    KARA("카라"),
    PENTAGON("펜타곤"),
    PINKLADY("핑크레이디"),
    PRISTIN("프리스틴"),
    SECHSKIES("젝스키스"),
    TAHITI("타히티"),
    TIARA("티아라");
    
    companion object {

        fun getIdolGroupByName(name: String): IdolImage? = values().firstOrNull { idolImage -> idolImage.idolName == name }

    }


}