package world
enum class QuestType(val description: String) {
    DELIVERY("Доставка предмета"),
    ELIMINATION("Устранение противника"),
    ESCORT("Сопровождение персонажа"),
    EXPLORE("Исследование новой территории"),
    BOSSFIGHT("Битва с боссом")
}
open class Mission(val title: String, val reward: Int) {
    open fun describe() {
        println("Миссия '$title', награда: $reward золотых")
    }
}
class Quest(
    title: String,
    val duration: Int,
    reward: Int,
    val difficulty: String,
    val questType: QuestType
) : Mission(title, reward) {
    override fun describe() {
        println("Квест '$title' на $duration часов, сложность: $difficulty, награда: $reward золотых")
        println("Тип квеста: ${questType.description}")
    }
    fun printInfo() {
        println("Название квеста: $title")
        println("Тип квеста: ${questType.description}")
        println("Время выполнения: $duration ч.")
        println("Награда: $reward золотых")
        println("Уровень сложности: $difficulty")
    }
}
fun showOnlyExploreQuests(quests: List<Quest>) {
    println("\n=== КВЕСТЫ ТИПА EXPLORE ===")
    val exploreQuests = quests.filter { it.questType == QuestType.EXPLORE }
    if (exploreQuests.isEmpty()) {
        println("Квестов типа EXPLORE не найдено")
    } else {
        exploreQuests.forEach { quest ->
            quest.describe()
            println("---")
        }
    }
}
fun main() {
    val quest1 = Quest(
        title = "Сопроводи торговца до деревни",
        duration = 4,
        reward = 120,
        difficulty = "Средний",
        questType = QuestType.ESCORT
    )
    val quest2 = Quest(
        title = "Исследовать древние руины",
        duration = 6,
        reward = 200,
        difficulty = "Высокий",
        questType = QuestType.EXPLORE
    )
    val quest3 = Quest(
        title = "Доставить посылку в соседний город",
        duration = 2,
        reward = 80,
        difficulty = "Лёгкий",
        questType = QuestType.DELIVERY
    )
    val quest4 = Quest(
        title = "Победить дракона в горах",
        duration = 8,
        reward = 500,
        difficulty = "Очень высокий",
        questType = QuestType.BOSSFIGHT
    )
    val quest5 = Quest(
        title = "Исследовать подземелье",
        duration = 5,
        reward = 180,
        difficulty = "Средний",
        questType = QuestType.EXPLORE
    )
    println("=== МЕТОД DESCRIBE() ===")
    quest1.describe()
    println()
    quest2.describe()
    println("\n=== МЕТОД PRINTINFO() ===")
    quest3.printInfo()
    println()
    quest4.printInfo()
    val allQuests = listOf(quest1, quest2, quest3, quest4, quest5)
    showOnlyExploreQuests(allQuests)
}