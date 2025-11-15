package characters
import world.Quest
import world.QuestType
import kotlin.system.exitProcess
class Trader(
    val name: String,
    private val quests: MutableList<Quest> = mutableListOf()
) {
    fun addQuest(quest: Quest) {
        quests.add(quest)
        println("Квест '${quest.title}' добавлен в список $name.")
    }
    fun showAvailableQuests() {
        println("\nДоступные квесты от $name:")
        if (quests.isEmpty()) {
            println("  Нет доступных квестов")
        } else {
            quests.forEachIndexed { index, quest ->
                println("  ${index + 1}. ${quest.title} (${quest.questType.description}) - Награда: ${quest.reward} золота")
            }
        }
    }
    fun giveQuest(index: Int): Quest? {
        return if (index in 1..quests.size) {
            quests[index - 1]
        } else {
            null
        }
    }
    fun removeQuest(index: Int): Boolean {
        return if (index in 1..quests.size) {
            val removedQuest = quests.removeAt(index - 1)
            println("Квест '${removedQuest.title}' удален из списка $name.")
            true
        } else {
            println("Некорректный индекс квеста")
            false
        }
    }
    fun start() {
        println("Добро пожаловать к торговцу $name!")
        while (true) {
            println("\n=== МЕНЮ ТОРГОВЦА ===")
            println("1 - Добавить квест")
            println("2 - Показать все квесты")
            println("3 - Взять квест (удалить из списка)")
            println("0 - Выйти")
            print("Ваш выбор: ")
            when (readln()) {
                "1" -> addQuestFromInput()
                "2" -> showAvailableQuests()
                "3" -> takeQuest()
                "0" -> {
                    println("Выход из меню торговца.")
                    break
                }
                else -> println("Некорректный ввод. Попробуйте снова.")
            }
        }
    }
    private fun addQuestFromInput() {
        println("\n--- Добавление нового квеста ---")
        print("Введите название квеста: ")
        val title = readln()
        print("Введите длительность (часы): ")
        val duration = readln().toIntOrNull() ?: run {
            println("Некорректная длительность")
            return
        }
        print("Введите награду (золото): ")
        val reward = readln().toIntOrNull() ?: run {
            println("Некорректная награда")
            return
        }
        print("Введите сложность: ")
        val difficulty = readln()
        println("Введите тип квеста (DELIVERY, ELIMINATION, ESCORT, EXPLORE, BOSSFIGHT): ")
        val typeInput = readln().uppercase()
        val questType = when (typeInput) {
            "DELIVERY" -> QuestType.DELIVERY
            "ELIMINATION" -> QuestType.ELIMINATION
            "ESCORT" -> QuestType.ESCORT
            "EXPLORE" -> QuestType.EXPLORE
            "BOSSFIGHT" -> QuestType.BOSSFIGHT
            else -> {
                println("Некорректный тип квеста")
                return
            }
        }
        val newQuest = Quest(title, duration, reward, difficulty, questType)
        addQuest(newQuest)
    }
    private fun takeQuest() {
        if (quests.isEmpty()) {
            println("Список квестов пуст.")
            return
        }
        showAvailableQuests()
        print("Введите номер квеста для взятия: ")
        val index = readln().toIntOrNull()
        if (index == null || index !in 1..quests.size) {
            println("Некорректный номер квеста")
            return
        }
        val quest = giveQuest(index)
        if (quest != null) {
            println("\nВы взяли квест:")
            quest.printInfo()
            removeQuest(index)
        }
    }
}
fun main() {
    val trader = Trader("Ральф")
    trader.start()
}