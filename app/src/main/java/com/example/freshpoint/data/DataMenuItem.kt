package com.example.freshpoint.data

import com.example.freshpoint.R
import com.example.freshpoint.model.BurgerMenuItem
import com.example.freshpoint.model.DrinkMenuItem
import com.example.freshpoint.model.SouseMenuItem

object DataMenuItem {

    val burgerMenuList = listOf(
        BurgerMenuItem(
            name = R.string.two_wopper_smoky_cheddar_at_a_discount,
            description = "Новый Воппер Смоки Чеддер — это легендарная говяжья котлета на огне, свежие сочные овощи и сразу два сырного слоя:" +
                    " тягучий чеддер и насыщенный соус Чеддер-Хамон с дымным акцентом. Больше сыра, больше вкуса," +
                    " больше того самого огня — бери, пока горячий",
            price = 499.99f,
            imageRes = R.drawable.resoice_2_wopper_smoky_cheddar
        ),
        BurgerMenuItem(
            name = R.string.anugs_pita_cedar_barbecue,
            description = "Сочная котлета из 100% мраморной говядины Ангус, хрустящий бекон, нежный сыр чеддер," +
                    " свежие овощи и двойной слой соуса Чеддер-Хамон — всё завернуто в мягкую Питу." +
                    " Вкус, достойный линейки Ангус. Формат — удобный, чтобы взять с собой. Всё лучшее в Пите",
            price = 529.99f,
            imageRes = R.drawable.resoice_anugs_pita_cedar_barbecue
        ),
        BurgerMenuItem(
            name = R.string.double_angus_cheddar_bacon,
            description = "Сразу две сочных котлеты Ангус из мраморной говядины, тягучий чеддер," +
                    " насыщенный соус Чеддер-Хамон и щедрая порция хрустящего жареного бекона." +
                    " Больше мяса, больше сыра, больше дымного характера в каждом укусе.",
            price = 669.99f,
            imageRes = R.drawable.resoice_double_angus_cheddar_bacon
        ),
        BurgerMenuItem(
            name = R.string.anugs_pita_parmesan,
            description = "Сочная котлета из 100% мраморной говядины Ангус, хрустящий бекон, нежный сыр чеддер," +
                    " свежие овощи и двойной слой соуса Чеддер-Хамон — всё завернуто в мягкую Питу." +
                    " Вкус, достойный линейки Ангус. Формат — удобный, чтобы взять с собой. Всё лучшее в Пите",
            price = 509.99f,
            imageRes = R.drawable.resoice_anugs_pita_parmesan
        ),
        BurgerMenuItem(
            name = R.string.spicy_angus_parmesan,
            description = "Сочная котлета Ангус из мраморной говядины, тягучий чеддер," +
                   " приправленная острым томатным соусом. Больше мяса, больше сыра, больше дымного характера в каждом укусе.",
            price = 579.99f,
            imageRes = R.drawable.resoice_spicy_angus_parmesan
        ),
        BurgerMenuItem(
            name = R.string.spicy_angus_parmesan_double,
            description = "Сразу две сочных котлеты Ангус из мраморной говядины, тягучий чеддер," +
                    " двойная порция соуса Чеддер-Хамон и щедрая порция хрустящего жареного бекона," +
                    " приправленная острым томатным соусом. Больше мяса, больше сыра, больше дымного характера в каждом укусе.",
            price = 679.99f,
            imageRes = R.drawable.resoice_spicy_angus_parmesan_double
        ),
        BurgerMenuItem(
            name = R.string.big_king_new,
            description = "Легендарный Биг Кинг возвращается в новой рецептуре! Двойная котлета," +
                    " свежие овощи и фирменный соус с огурчиками в картофельной булочке — обновлённая классика с сочным вкусом," +
                    " который запомнится.",
            price = 199.99f,
            imageRes = R.drawable.resoice_big_king_new
        ),
        BurgerMenuItem(
            name = R.string.big_king_smokki_cheader,
            description = "Легендарный Биг Кинг возвращается в новой рецептуре! Двойная котлета, свежие овощи, нежный сыр чеддер и дымный" +
                    " соус Чеддер-Хамон в картофельной булочке — обновлённая классика с неповторимым вкусом, который запомнится.",
            price = 239.99f,
            imageRes = R.drawable.resoice_big_king_smokki_cheader
        ),
        BurgerMenuItem(
            name = R.string.chicken_roll_smokey_cedar,
            description = "Нежные куриные стрипсы, тягучий чеддер и щедрая порция соуса Чеддер-Хамон в мягкой" +
                    " тортилье с хрустящими овощами. Удобный формат — большой сырный вкус.",
            price = 349.99f,
            imageRes = R.drawable.resoice_chicken_roll_smokey_cedar
        ),
        BurgerMenuItem(
            name = R.string.shrip_chiken_ceddr_roll,
            description = "Сочные креветки, нежный чеддер и насыщенный соус Чеддер-Хамон" +
                    " в мягкой тортилье с хрустящими овощами. Морской характер с ярким сырным акцентом.",
            price = 429.99f,
            imageRes = R.drawable.resoice_shrip_chiken_ceddr_roll
        ),
        BurgerMenuItem(
            name = R.string.chicken_smoky_cedar,
            description = "Сочные куриные стрипсы, расплавленный чеддер и соус Чеддер-Хамон" +
                    " со свежими овощами. Нежная курочка с сырно-дымными нотами.",
            price = 369.99f,
            imageRes = R.drawable.resoice_chicken_smoky_cedar
        ),
        BurgerMenuItem(
            name = R.string.resoice_double_wopper_smoky_cedar,
            description = "Две говяжьи котлеты на огне, двойная порция соуса Чеддер-Хамон" +
                    " и тягучий чеддер — максимум дымно-сырного вкуса в культовой сборке Воппера. Больше сыра," +
                    " больше вкуса, больше того самого огня — бери, пока горячий.",
            price = 519.99f,
            imageRes = R.drawable.resoice_double_wopper_smoky_cedar
        ),
        BurgerMenuItem(
            name = R.string.spicy_chickenburger_with_mushrooms,
            description = "Хрустящие куриные наггетсы со свежим салатом айсберг, " +
                    "жареными лисичками, пикантным острым соусом и соусом цезарь в нежной булочке",
            price = 129.99f,
            imageRes = R.drawable.resoice_spicy_chickenburger_with_chanterelles
        ),
        BurgerMenuItem(
            name = R.string.spicy_cheeseburger_with_mushrooms,
            description = "Фирменная говяжья котлета с ломтиком сыра Чеддер, жареными лисичками," +
                    " хрустящим луком фри, пикантным острым соусом и соусом цезарь в нежной булочке",
            price = 149.99f,
            imageRes = R.drawable.resoice_spicy_cheeseburger_with_mushrooms
        ),

    )
    val souseMenuList = listOf(
        SouseMenuItem(
            id = 22221,
            name = R.string.souse_grill,
            description = "Он вернулся. Он премиальный. Необыкновенный соус для твоих закусок — с ароматом пикника." +
                    " Приготовлен по эксклюзивному рецепту нашего шефа.",
            price = 74.99f,
            imageRes = R.drawable.resource_grill
        ),
        SouseMenuItem(
            id = 22222,
            name = R.string.souse_caesar,
            description = "Добавьте в любимые закуски приятный аромат," +
                    " остроту и восхитительный нежный вкус соуса. Это то, без чего не обойтись!",
            price = 74.99f,
            imageRes = R.drawable.resoice_caesar
        ),
        SouseMenuItem(
            id = 22223,
            name = R.string.souse_cheesy,
            description = "Добавьте в любимые закуски приятный аромат," +
                    " остроту и восхитительный нежный вкус соуса. Это то, без чего не обойтись!",
            price = 69.99f,
            imageRes = R.drawable.resource_cheesy
        ),
        SouseMenuItem(
            id = 22224,
            name = R.string.souse_mustard,
            description = "Добавьте в любимые закуски приятный аромат," +
                    " остроту и восхитительный нежный вкус соуса. Это то, без чего не обойтись!",
            price = 69.99f,
            imageRes = R.drawable.resource_mustard
        ),
        SouseMenuItem(
            id = 22225,
            name = R.string.souse_parmejano,
            description = "Добавьте в любимые закуски приятный аромат," +
                    " остроту и восхитительный нежный вкус соуса. Это то, без чего не обойтись!",
            price = 74.99f,
            imageRes = R.drawable.resource_parmejano
        ),
        SouseMenuItem(
            id = 22226,
            name = R.string.souse_tomato,
            description = "Сделай остро! Бургеры и закуски станут ещё жарче с нашим острым томатным соусом!" +
                    " В составе — чёрный перец, чили и мускатный орех.",
            price = 74.99f,
            imageRes = R.drawable.resource_tomato
        ),
    )
    val drinkMenuList = listOf(
        DrinkMenuItem(
            id = 33331,
            name = R.string.small_drink_cola,
            description = "Освежающий газированный напиток с классическим вкусом",
            price = 149.99f,
            imageRes = R.drawable.resource__colla_small
        ),
        DrinkMenuItem(
            id = 33332,
            name = R.string.small_drink_cola_light,
            description = "Освежающий газированный напиток с классическим вкусом",
            price = 149.99f,
            imageRes = R.drawable.resoice__colla_light
        ),
        DrinkMenuItem(
            id = 33333,
            name = R.string.small_drink_cola_chery,
            description = "Освежающий газированный напиток с классическим вкусом",
            price = 149.99f,
            imageRes = R.drawable.resoice__colla_cherry
        ),
        DrinkMenuItem(
            id = 33334,
            name = R.string.small_drink_7up,
            description = "Освежающий газированный напиток с классическим вкусом",
            price = 149.99f,
            imageRes = R.drawable.resoice__7up
        ),
        DrinkMenuItem(
            id = 33335,
            name = R.string.small_drink_fanta,
            description = "Освежающий газированный напиток с классическим вкусом",
            price = 149.99f,
            imageRes = R.drawable.resoice__fanta
        ),
        DrinkMenuItem(
            id = 33336,
            name = R.string.small_drink_pepsi,
            description = "Освежающий газированный напиток с классическим вкусом",
            price = 149.99f,
            imageRes = R.drawable.resoice__pepsi
        ),
        DrinkMenuItem(
            id = 33337,
            name = R.string.small_drink_swheppes,
            description = "Освежающий газированный напиток с классическим вкусом",
            price = 149.99f,
            imageRes = R.drawable.resoice__swheepes
        ),

    )
}