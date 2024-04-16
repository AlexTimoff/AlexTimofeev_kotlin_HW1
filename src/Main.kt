import java.lang.System.exit
import java.util.regex.Pattern
import kotlin.system.exitProcess

/*
Домашнее задание №1

Написать программу, которая обрабатывает введённые пользователем в консоль команды:
exit
help
add <Имя> phone <Номер телефона>
add <Имя> email <Адрес электронной почты>
После выполнения команды, кроме команды exit, программа ждёт следующую команду.
 */

fun main(){
    execution()
}

fun execution(){
    var work: Boolean=true
    while (work) {
        menu()
        var userInput: String = ""
        userInput = readLine().toString()
        when (userInput) {
            "help" -> help()
            "exit" ->{
                exit()
                work=false
            }
            else -> addInformation(userInput)
        }

    }

}

fun menu(){
    println("Здравствуйте. Введите соответствующий пункт меню:")
    println("help")
    println("add User phone UserPhone")
    println("add User email UserEmail")
    println("exit")
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
}

fun help(){
    println("Команда help выводит вспомогательную информацию.")
    println("В рамках команды <add User phone UserPhone> пользователю добавляется номер телефона." +
            " User - имя пользователя - любое слово. Номер телефона может содержать знак +, затем идут цифры.")
    println("в рамках команды add User email UserEmail добавляется пользователю электронная почта." +
            " Электронный адрес должен содержать три последовательности букв, разделённых символами @ и точкой.")
    println("Команда exit обеспечивает выход из приложения.")
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
}

fun addInformation(userInput: String){
    val words = userInput.split(" ")
    if (words.size != 4) {
        error()
    }
    else{
        if (words[0].contains("add")&&words[2].contains("phone")){
            addPhone(words[1],words[3])
        }
        else if (words[0].contains("add")&&words[2].contains("email")){
            addEmail(words[1],words[3])
        }
        else{
            error()
        }
    }
}

// Добавляем номер телефона, проверяя на соответствие двум шаблонам вводна тел. номера (по условию задачи номер телефона может начинаться с "+").
fun addPhone(name: String,phone: String){
    val phonePattern1 = Regex("[+]+\\d+")
    val phonePattern2 = Regex("\\d+")
    if (phonePattern1.matches(phone)||phonePattern2.matches(phone) ) {
        println("Добавлен контакт: $name, телефон: $phone")
    } else {
        println("Ошибка: неверный формат номера телефона")
    }
}

// Добавляем номер телефона, проверяя на соответствие двум шаблонам вводна эл. почты
fun addEmail(name: String,email: String){
    val emailPattern = Regex("[a-zA-z0-9]+@[a-zA-z0-9]+[.]([a-zA-z0-9]{2,4})")
    if (emailPattern.matches(email)) {
        println("Добавлен контакт: $name, email: $email")
    } else {
        println("Ошибка: неверный формат электронной почты")
    }
}


fun exit(){
    println("Досвидания! Были рады видеть Вас!")
}

fun error(){
    println("Ошибка! Попробуйте снова!")
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
}




