package org.acme

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/names")
class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello RESTEasy ${getNames()}"

    fun getNames(): List<String> {
        val foreNames = arrayOf("Rohan","Nathaniel","Anthony","Chris","Jonathan","Lemur","Harry","Percy","Peregrine","James","Jamie","Sidney","Gabriel","Leyton","Curtley","Jarvis")
        val middleNames = arrayOf("Rohan","Nathaniel","Tony","Chris","Jonathan","Lemur","Harry","Percy","Peregrine","James","Jamie","Sidney","Gabriel","Leyton","Curtley","Jarvis")

        foreNames.sort()
        middleNames.sort()

        var count = 0

        val names = mutableListOf<String>()
        for (name1 in foreNames) {
            count++
            val syllables1 = syllableCount(name1)

            for (name2 in middleNames) {
                if (name1 != name2) {
                    val syllables2 = syllableCount(name2)

                    if (name1[0] == name2[0] || (syllables1 == 1 && syllables2 == 1) || (syllables1 == 1 && syllables2 >= 3) || (syllables1 >= 3 && syllables2 >= 3) || (syllables1 >= 3 && syllables2 == 1)) {
                        continue
                    }

                    count++
                    names.add("$name1 $name2 Jordan-Regan")
                }
            }
        }

        return names
    }

    fun isVowel(inChar: Char) : Boolean {
        return (inChar == 'a' || inChar == 'e' || inChar == 'i' || inChar == 'o' || inChar == 'u')
    }

    fun syllableCount(inStr: String): Int {
        if (inStr == "Anthony" || inStr == "Gabriel") {
            return 3
        }

        if (inStr == "James") {
            return 1
        }

        var syllables = 0
        var lastChar = '.'
        var wasVowel = false

        for (idx in inStr.indices) {
            val c = inStr[idx]

            if (wasVowel && ((lastChar == 'u' && c == 'a') || (lastChar == 'i' && c == 'a'))) {
                syllables++
            }
            else if (isVowel(c) || c == 'y') {
                if (!wasVowel && (c != 'e' || idx < inStr.length - 1)) {
                    syllables++
                    wasVowel = true
                }
            }
            else {
                wasVowel = false
            }

            lastChar = c
        }

        return if (syllables == 0) 1 else syllables
    }
}