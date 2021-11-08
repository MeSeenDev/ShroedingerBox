package quests.strings



/**
 * @author Vyacheslav Doroshenko
 */

fun reverse(x: Int): Int =
    x.toString().run {
        try {
            if(contains("-")||contains("+")){
                val pref = substring(0..0)
                return@run (pref +  replace("""\D""".toRegex(),"").reversed()).toInt()
            }
            reversed().toInt()
        }catch (thr: Throwable){
            0
        }

    }

