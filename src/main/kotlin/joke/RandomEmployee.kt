package joke

/**
 * @author Vyacheslav Doroshenko
 */
class RandomEmployee(private vararg val employees: Employee) {

    val randomEmployee: Employee get() = employees.random();

}


fun randomOf(vararg names: String):RandomEmployee{
    val employees = names.map { Employee(name= it) }.toTypedArray()
    return RandomEmployee(*employees)
}