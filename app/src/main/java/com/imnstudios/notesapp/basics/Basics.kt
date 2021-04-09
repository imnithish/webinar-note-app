import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

// main()
fun main() {
    // Variables, data types, lateinit, arrays, etc.
//    val a: Int
//    var b: Int
//    lateinit var x: String
//    val s = arrayListOf<Int>(4,4)
//    s.add(4)


//    println("Hello world")
//    val x = PrintMe()
//    x.print("Hello")
//
//    val y = PrintMe2("Hello")
//    y.print("World")

//    val scanner = Scanner(System.`in`)
//    val number = scanner.nextInt()
//    val result = doSomethingCool(number)
//    println(result)

    // higher order functions
   // A higher-order function is a function that takes functions as parameters, or returns a function.
    getARandomNumberFromServer(1, 99) { result ->
        if (result % 2 == 0)
            println("number is even")
        else
            println("number is odd")

    }


    // Inheritance
//    val animals = Animals()
//    animals.Species("cat")

    // Inheritance abstract class
//    val animals = Animals()
//    animals.species("cat")

    // Interface
//    val ageImplementation = AgeImplementation()
//    ageImplementation.isUnderAge(88)

}

// Classes
class PrintMe {
    fun print(message: String) {
        println(message)
    }
}

// Classes with constructor
class PrintMe2(private val message0: String) {
    fun print(message: String) {
        println("$message0, $message")
    }
}

// Functions
fun doSomethingCool(value: Int): String {
    if (value % 2 == 0)
        return "even"
    return "odd"
}

// lambda
fun getARandomNumberFromServer(start: Int, end: Int, callback: (number: Int) -> Unit) {
    val aRandomNumber = (start..end).random()
    callback(aRandomNumber)
}

// Inheritance
//base class
//open class LivingBeings {
//    open fun Species(name: String) {
//        if (name == "cat")
//            println("cat is an animal")
//    }
//}
//
////derived class
//class Animals : LivingBeings() {
//    override fun Species(name: String) {
//        super.Species(name)
//    }
//}

// Inheritance abstract class
// An abstract member function doesn’t have a body, and it must be implemented in the derived class.
//abstract class LivingBeings(val name: String?=null){
//    abstract fun species(name: String)
//}
//
//class Animals : LivingBeings(){
//    override fun species(name: String) {
//        if (name=="cat")
//            print("cat is an animal")
//    }
//
//
//}


// Interface
// cant be instantiated directly
// define a form of behavior that the implementing types have to follow.
// doesn't hold any state
interface Age {
    fun isUnderAge(age: Int)
}

class AgeImplementation : Age {
    override fun isUnderAge(age: Int) {
        if (age < 18)
            print("underage") else println("not underaged")
    }

}


