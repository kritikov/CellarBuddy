package com.example.cellarbuddy.data.db

import com.example.cellarbuddy.data.models.Category
import com.example.cellarbuddy.data.models.CategoryTypeCrossRef
import com.example.cellarbuddy.data.models.Drink
import com.example.cellarbuddy.data.models.DrinkTypeCrossRef
import com.example.cellarbuddy.data.models.Type

object InitialDataProvider {
    suspend fun seedDatabase(database: AppDatabase) {

        database.categoryDao().insertCategories(listOf(
            Category(1, "Wine"),
            Category(2, "Whisky"),
            Category(3, "Various"),
        ))
        database.typeDao().insertTypes(listOf(
            Type(101, "Agiorgitiko", "Saint George"),
            Type(102, "Assyrtiko", ""),
            Type(103, "Bone Dry", ""),
            Type(104, "Brut", ""),
            Type(105, "Brut Nature", ""),
            Type(106, "Cabernet", ""),
            Type(107, "Cabernet Dorsa", ""),
            Type(108, "Cabernet Franc", ""),
            Type(109, "Cabernet Mitos", ""),
            Type(110, "Cabernet Sauvignon", ""),
            Type(111, "Cava", ""),
            Type(112, "Champagne", ""),
            Type(113, "Chardonnay", ""),
            Type(114, "Demi-Sec", "Demi Sec"),
            Type(115, "Doux", ""),
            Type(116, "Dry", ""),
            Type(117, "Extra Brut", ""),
            Type(118, "Extra Dry", "Semi-dry, Semi dry, Off-Dry"),
            Type(119, "Grenache", ""),
            Type(120, "Madeira", ""),
            Type(121, "Mavroudi", "Mavroud, Mavrud"),
            Type(122, "Merlot", "Merlot grape"),
            Type(123, "Moschofilero", ""),
            Type(124, "Muscat", ""),
            Type(125, "Pinot Noir", "Pinot"),
            Type(126, "Prosecco", ""),
            Type(127, "Red", "rouge"),
            Type(128, "Riesling", "Riesling grape"),
            Type(129, "Roditis", ""),
            Type(130, "Rose", "rosé"),
            Type(131, "Ruby Cabernet", ""),
            Type(132, "Sauvignon Blanc", ""),
            Type(133, "Semi-sweet", "Semi sweet"),
            Type(134, "Sherry", ""),
            Type(135, "Shiraz", "Syrah"),
            Type(136, "Sparkling", ""),
            Type(137, "Sweet", ""),
            Type(138, "Syrah", "Shiraz"),
            Type(139, "Very Sweet", "Dessert"),
            Type(140, "Vinsanto", ""),
            Type(141, "White", ""),
            Type(142, "Xinomavro", ""),

            Type(201, "Blended", ""),
            Type(202, "Bourbon", ""),
            Type(203, "Canadian", ""),
            Type(204, "Cask Strength", ""),
            Type(205, "Double Cask", "Double Cask Matured, Dual Cask, Two-Cask Aged, Double Wood"),
            Type(206, "Double Mellowed", "Double Aged, Double Matured, Twice Mellowed, Double Smooth, Double Refined, Double Cask Matured, Twice Aged, Extra Mellowed"),
            Type(207, "Irish", ""),
            Type(208, "Japanese", ""),
            Type(209, "Malt", ""),
            Type(210, "No Age Statement", ""),
            Type(211, "Peated", ""),
            Type(212, "Pot Still", ""),
            Type(213, "Rye", ""),
            Type(214, "Scotch", ""),
            Type(215, "Single", ""),
            Type(216, "Single Grain", ""),
            Type(217, "Single Pot Still", ""),
            Type(218, "Tennessee", "")
        ))
        database.categoryDao().insertCategoryTypeCrossRefs(listOf(
            CategoryTypeCrossRef(categoryId = 1, typeId = 101),
            CategoryTypeCrossRef(categoryId = 1, typeId = 102),
            CategoryTypeCrossRef(categoryId = 1, typeId = 103),
            CategoryTypeCrossRef(categoryId = 1, typeId = 104),
            CategoryTypeCrossRef(categoryId = 1, typeId = 105),
            CategoryTypeCrossRef(categoryId = 1, typeId = 106),
            CategoryTypeCrossRef(categoryId = 1, typeId = 107),
            CategoryTypeCrossRef(categoryId = 1, typeId = 108),
            CategoryTypeCrossRef(categoryId = 1, typeId = 109),
            CategoryTypeCrossRef(categoryId = 1, typeId = 110),
            CategoryTypeCrossRef(categoryId = 1, typeId = 111),
            CategoryTypeCrossRef(categoryId = 1, typeId = 112),
            CategoryTypeCrossRef(categoryId = 1, typeId = 113),
            CategoryTypeCrossRef(categoryId = 1, typeId = 114),
            CategoryTypeCrossRef(categoryId = 1, typeId = 115),
            CategoryTypeCrossRef(categoryId = 1, typeId = 116),
            CategoryTypeCrossRef(categoryId = 1, typeId = 117),
            CategoryTypeCrossRef(categoryId = 1, typeId = 118),
            CategoryTypeCrossRef(categoryId = 1, typeId = 119),
            CategoryTypeCrossRef(categoryId = 1, typeId = 120),
            CategoryTypeCrossRef(categoryId = 1, typeId = 121),
            CategoryTypeCrossRef(categoryId = 1, typeId = 122),
            CategoryTypeCrossRef(categoryId = 1, typeId = 123),
            CategoryTypeCrossRef(categoryId = 1, typeId = 124),
            CategoryTypeCrossRef(categoryId = 1, typeId = 125),
            CategoryTypeCrossRef(categoryId = 1, typeId = 126),
            CategoryTypeCrossRef(categoryId = 1, typeId = 127),
            CategoryTypeCrossRef(categoryId = 1, typeId = 128),
            CategoryTypeCrossRef(categoryId = 1, typeId = 129),
            CategoryTypeCrossRef(categoryId = 1, typeId = 130),
            CategoryTypeCrossRef(categoryId = 1, typeId = 131),
            CategoryTypeCrossRef(categoryId = 1, typeId = 132),
            CategoryTypeCrossRef(categoryId = 1, typeId = 133),
            CategoryTypeCrossRef(categoryId = 1, typeId = 134),
            CategoryTypeCrossRef(categoryId = 1, typeId = 135),
            CategoryTypeCrossRef(categoryId = 1, typeId = 136),
            CategoryTypeCrossRef(categoryId = 1, typeId = 137),
            CategoryTypeCrossRef(categoryId = 1, typeId = 138),
            CategoryTypeCrossRef(categoryId = 1, typeId = 139),
            CategoryTypeCrossRef(categoryId = 1, typeId = 140),
            CategoryTypeCrossRef(categoryId = 1, typeId = 141),
            CategoryTypeCrossRef(categoryId = 1, typeId = 142),

            CategoryTypeCrossRef(categoryId = 2, typeId = 201),
            CategoryTypeCrossRef(categoryId = 2, typeId = 202),
            CategoryTypeCrossRef(categoryId = 2, typeId = 203),
            CategoryTypeCrossRef(categoryId = 2, typeId = 204),
            CategoryTypeCrossRef(categoryId = 2, typeId = 205),
            CategoryTypeCrossRef(categoryId = 2, typeId = 206),
            CategoryTypeCrossRef(categoryId = 2, typeId = 207),
            CategoryTypeCrossRef(categoryId = 2, typeId = 208),
            CategoryTypeCrossRef(categoryId = 2, typeId = 209),
            CategoryTypeCrossRef(categoryId = 2, typeId = 210),
            CategoryTypeCrossRef(categoryId = 2, typeId = 211),
            CategoryTypeCrossRef(categoryId = 2, typeId = 212),
            CategoryTypeCrossRef(categoryId = 2, typeId = 213),
            CategoryTypeCrossRef(categoryId = 2, typeId = 214),
            CategoryTypeCrossRef(categoryId = 2, typeId = 215),
            CategoryTypeCrossRef(categoryId = 2, typeId = 216),
            CategoryTypeCrossRef(categoryId = 2, typeId = 217),
            CategoryTypeCrossRef(categoryId = 2, typeId = 218),

            ))
        database.drinkDao().insertDrinks(listOf(
            // Whisky
            Drink(
                1,
                "Glenfiddich 12",
                categoryId = 2,
                comments = "Scotch whisky, aged for 12 years in oak casks"
            ),
            Drink(
                2,
                "Jameson Irish Whiskey",
                categoryId = 2,
                comments = "Smooth Irish whiskey triple distilled for balance"
            ),

            // Wine
            Drink(
                3,
                "Château Margaux",
                categoryId = 1,
                comments = "French Bordeaux red wine, elegant and complex"
            ),
            Drink(
                4,
                "Assyrtiko Santorini",
                categoryId = 1,
                comments = "Greek dry white wine from Santorini, crisp and mineral"
            ),

            // Various
            Drink(
                5,
                "Beefeater",
                categoryId = 3,
                comments = "Classic London dry gin known for its bold juniper flavor"
            ),
            Drink(
                6,
                "Baileys Irish Cream",
                categoryId = 3,
                comments = "Cream liqueur blending Irish whiskey and cream"
            )
        ))

        database.drinkDao().insertDrinkTypeCrossRefs(listOf(
            // Glenfiddich 12 -> Scotch
            DrinkTypeCrossRef(drinkId = 1, typeId = 214),

            // Jameson -> Irish
            DrinkTypeCrossRef(drinkId = 2, typeId = 207),

            // Château Margaux -> Cabernet Sauvignon (κρασί Bordeaux)
            DrinkTypeCrossRef(drinkId = 3, typeId = 110),

            // Assyrtiko Santorini -> Assyrtiko
            DrinkTypeCrossRef(drinkId = 4, typeId = 102),

            // Beefeater -> Dry
            DrinkTypeCrossRef(drinkId = 5, typeId = 116),

            // Baileys Irish Cream -> Sweet
            DrinkTypeCrossRef(drinkId = 6, typeId = 137),
        ))
    }
}