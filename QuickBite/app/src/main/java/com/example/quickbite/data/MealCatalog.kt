package com.example.quickbite.data

/**
 * Static catalog of meal suggestions. Kept separate from logic so it's
 * easy to extend or later replace with a database-backed source.
 */
object MealCatalog {
    val meals = listOf(
        Meal(1, "Pasta bolognaise", "🍝", "Italien"),
        Meal(2, "Pizza margherita", "🍕", "Italien"),
        Meal(3, "Sushi", "🍣", "Japonais"),
        Meal(4, "Burger maison", "🍔", "Fast food"),
        Meal(5, "Salade César", "🥗", "Léger"),
        Meal(6, "Couscous", "🍲", "Maghrébin"),
        Meal(7, "Poulet rôti & frites", "🍗", "Classique"),
        Meal(8, "Curry de légumes", "🍛", "Indien"),
        Meal(9, "Ramen", "🍜", "Japonais"),
        Meal(10, "Tacos", "🌮", "Mexicain"),
        Meal(11, "Omelette", "🍳", "Rapide"),
        Meal(12, "Soupe maison", "🥣", "Léger"),
        Meal(13, "Quiche lorraine", "🥧", "Français"),
        Meal(14, "Falafel", "🧆", "Moyen-Orient"),
        Meal(15, "Steak & légumes", "🥩", "Classique"),
        Meal(16, "Poke bowl", "🥙", "Sain"),
        Meal(17, "Croque-monsieur", "🥪", "Rapide"),
        Meal(18, "Risotto", "🍚", "Italien")
    )
}
