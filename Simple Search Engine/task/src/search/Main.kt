package search

import java.io.File

fun main(args: Array<String>)  {
    var strList = arrayListOf<String>()

    var inputF = File(args[1])
    if (inputF.exists()) {
        for (line in inputF.readLines()) {
            if (line.isNotEmpty()) {
                strList.add(line)
            }
        }
    } else println("error file")

    var dictionary = toDictionary(strList)


    var theEns = false
    while (!theEns) {
        menu()
        var numMenu = readln()
        when (numMenu) {
            "1" -> {
                //found(strList)
                println("Select a matching strategy: ALL, ANY, NONE")
                var strategy = readln()
                when (strategy) {
                    "ALL" -> {
                        found2(dictionary, strList)
                    }
                    "ANY" -> {
                        foundANY(dictionary, strList)
                    }
                    "NONE" -> {
                        foundNONE(dictionary, strList)
                    }
                    else -> println("error")
                }
            }
            "2" -> {
                printList(strList)
            }
            "0" -> {
                println("Bye!")
                break
            }
            else -> {
                println("Incorrect option! Try again.")
                continue
            }
        }
    }
}

fun foundNONE(dictionary: MutableMap<String, ArrayList<Int>>, strList: ArrayList<String>) {
    println("Enter a name or email to search all matching people.")
    var strFound = readln().lowercase()
    var strFoundList = strFound.split(' ')
    var rezList = strList.toMutableSet()

    for (str in strFoundList) {
        if (dictionary.containsKey(str)) {
            for (el in dictionary[str]!!) {
                rezList.remove(strList[el])
            }

        }
    }
    if (rezList.size == 0) println("No matching people found.")
    else {
        println("${rezList.size} persons found:")
        for (element in rezList) {
            println(element)
        }
    }
}

fun foundANY(dictionary: MutableMap<String, ArrayList<Int>>, strList: ArrayList<String>) {
    println("Enter a name or email to search all matching people.")
    var strFound = readln().lowercase()
    var strFoundList = strFound.split(' ')
    var rezList = mutableSetOf<String>()

    for (str in strFoundList) {
        if (dictionary.containsKey(str)) {
            for (el in dictionary[str]!!) {
                rezList.add(strList[el])
            }

        }
    }
    if (rezList.size == 0) println("No matching people found.")
    else {
        println("${rezList.size} persons found:")
        for (element in rezList) {
            println(element)
        }
    }
}


fun toDictionary(strList: ArrayList<String>): MutableMap<String, ArrayList<Int>> {
    var dictionary = mutableMapOf<String, ArrayList<Int>>()
    for (element in strList) {
        var count = 0
        var subStr = element.split(' ').map { it.lowercase() }
        for (i in subStr) {
            if (dictionary.containsKey(i)) {
                var newList = dictionary[i]
                if (newList != null) {
                    newList.add(strList.indexOf(element))
                }
                newList?.let { dictionary.put(i, it) }
            } else {
                dictionary[i] = arrayListOf(strList.indexOf(element))
            }
        }
    }
    return dictionary
}

fun found2(dictionary: MutableMap<String, ArrayList<Int>>, strList: List<String>) {
    println("Enter a name or email to search all matching people.")
    var strFound = readln().lowercase()
    if (dictionary.containsKey(strFound)) {
        var count = dictionary[strFound]?.size
        println("$count person found:")
        for (el in dictionary[strFound]!!) {
            println(strList[el])
        }
    } else {
        println("No matching people found.")
    }
}

fun printList(strList: ArrayList<String>) {
    println()
    println("=== List of people ===")
    for (i in 0..strList.size - 1) {
        println("${strList[i]}")
    }
}

fun found(strList: List<String>){
    println("Enter data to search people:")
    var strFound = readln()
    var rez : Boolean = false
    var one = 1

        for (element in strList) {
            if ( element.lowercase().indexOf(strFound.lowercase()) != -1) {
                if (one == 1) println("People found:")
                println(element)
                rez = true
                one++
            }
        }

    if (rez == false) println("No matching people found.")
}

fun menu(){
    println()
    println("=== Menu ===\n" +
            "1. Find a person\n" +
            "2. Print all people\n" +
            "0. Exit")

}