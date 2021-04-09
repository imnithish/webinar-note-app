fun main() {

//    val a = 2
//    val b = 4
//    whichIsBig(a, b)


    // loops

    // for loop

    val items = listOf("car", "cycle", "bike")
//    for (item in items) {
//        println(item)
//    }

//    for (i in items.indices) {
//        println(items[i])
//    }
//
//    var i = 0
//    while (i < items.size) {
//        print(items[i])
//        i++
//    }

//    var i = 0
//    do {
//        print(items[i])
//        i++
//    } while (i < items.size)

    //ranges

//    for (i in 1..20 step 2){
//        print(i)
//    }

//    for (i in 20 downTo 0){
//        print(i)
//    }
//
//    repeat(5) {
//        println(it)
//    }
//
//    items.filter {
//        it.startsWith("c")
//    }.sorted().map {
//        it.toUpperCase()
//    }
//        .forEach { println(it) }


    // null check
    val a = getARandomNumber(5)
    a?.let {

    } ?: run {

    }

}

fun getARandomNumber(a: Int): Int? {
    if (a % 2 == 0)
        return 0

    return null
}


// conditional statements
fun whichIsBig(a: Int, b: Int) {
//    if (a > b)
//        print("a is big")
//    else if (a < b)
//        print("b is big")
//    else
//        print("a and b are equal")


//    when {
//        a > b -> print("a is big")
//        a < b -> print("b is big")
//        else -> print("a and b are equal")
//    }

    when (val result = (a % 2 == 0)) {
        true -> print("even, $result")
        else -> print("old")
    }


}

