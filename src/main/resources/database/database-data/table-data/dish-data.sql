--liquibase formatted sql

--changeSet abeznos:dishes-data-01 logicalFilePath:database\database-data\dishes-data
insert into dishes (name, menu_category_id, description, ingredients, photo, price, calories, spicy, for_vegans, without_sugar, without_gluten, star_one, star_two,star_three,star_four,star_five ) values ('Греческий', 1, 'Фирменный салат из свежих овощей с добавлением маслин, маринованных каперсов и сыра фетаки. Заправляется оливковым маслом.', 'помидоры, огурцы, сладкий перец, листья салата, красный лук, маслины, маринованные каперсы, сыр фетаки', 'salat_01.png', '550', '325', false, false, true, true, 1, 1, 5, 100, 145),
('Цезарь', 1, 'Популярный салат Цезарь - с курицей, помидорами и яйцами. Запрвляется соусом с чесноком и лимонным соком.', 'курица, сыр твёрдый, батон, салат зеленый, помидоры черри, яйца, ядра грецких орехов, масло оливковое, чеснок, горчица, сок лимона, соус Цезарь', 'salat_02.png', '430', '250', true, false, true, true, 1, 1, 5, 40, 240),
('Из кальмаров', 1, 'Традиционный вариант салата с кальмарами. Ингредиенты гарантируют сочный, нежный вкус, никогда не выйдут из моды. ', 'ушки кальмаров, яйца, огурец, зеленый горошек', 'salat_03.png', '630', '100', false, false, true, true, 1, 1, 5, 70, 350),
('Оливье', 1, 'Классический салат Оливье. Заправляется фирменным соусом.', 'картофель, морковь, яйца, колбаса, огурцы маринованные, горошек зеленый', 'salat_04.png', '450', '150', false, false, true, true, 1, 1, 5, 140, 485),
('Мимоза', 1, 'Салат мимоза, главными ингредиентами в котором служат рыба, куриные яйца лук и заправка. Название блюда полностью отражает его вид - салат изображает в себе цветы мимозы на снежной поверхности.', 'рыба, картофель, сыр твердый, яйца, зелень', 'salat_05.png', '400', '275', false, false, true, true, 1, 1, 5, 47, 321),
('Гранатовый', 1, 'Слоеный салат, оформленный оригинальным способом. Название салата произошло из-за его кольцевидной формы и из-за того, что его поверхность украшена зернами граната.', 'куриное филе, картофель, свекла, морковь, яйца, гранат, орех грецкий', 'salat_06.png', '520', '110', false, false, false, false, 1, 1, 5, 89, 231),
('Грибная поляна', 1, 'Популярный салат грибная поляна.', 'грибы маринованные, куриное мясо отварное, сыр твердый, яйца,  картофель, огурцы соленые', 'salat_07.png', '500', '115', false, false, false, false, 1, 1, 5, 45, 157),
('Летний', 1, 'Очень вкусный салат со свежими овощами, заправленный оригинальным соусом. ', 'кукуруза вареная, капуста, огурец, укроп', 'salat_08.png', '300', '65', false, false, false, false, 1, 1, 5, 78, 421),
('Сэндвич с семгой', 2, 'Сэндвич с семгой – отличный вариант здорового и вкусного завтрака.', 'семга слабосоленая, пшеничная булочка, сыр , каперсы, листья салата, помидор', 'starter_01.png', '350', '240', false, false, true, false, 1, 1, 5, 89, 420),
('Печеночный паштет', 2, 'Вкусный паштет, который можно съесть на завтрак, как закуску или перекус, понравится и взрослым и детям.', 'говяжья печень, сливочное масло, лук репчатый,  морковь', 'starter_02.png', '300', '180', false, false, true, true, 1, 1, 5, 47, 301),
('Мусс из форели', 2, 'Оригинальный мусс. Может быть подан на крекерах, в тарталетках или на цельнозерновом хлебе. ', 'сыр творожный, форель слабосоленая, каперсы, укроп', 'starter_03.png', '320', '230', true, false, true, false, 1, 1, 5, 110, 424),
('Гренки с шампиньонами', 2, 'Ароматные, хрустящие гренки с шампиньонами.', 'хлеб ржаной, филе куриное отварное, шампиньоны, тимьян сушеный, сыр твердых сортов', 'starter_04.png', '320', '290', false, false, true, false, 1, 1, 5, 78, 241),
('Красная уха', 3, 'Фирменная красная уха, которая может служить вкусным и сытным обедом.', 'рыбный бульон, 	филе нерки на коже, картофель, лук, стебель лука-порея', 'hot_01.png', '600', '305', true, false, true, true, 1, 1, 5, 111, 210),
('Овощная запеканка', 3, 'Легкая и полезная запеканка с молодыми кабачками. ', 'цветная капуста, кабачки, яйца, твердый сыр', 'hot_02.png', '500', '180', false, true, true, false, 1, 1, 5, 41, 329),
('Голубцы с мясом', 3, 'Традиционное блюдо русской кухни. Подается с оригинальным соусом.', 'рис, капуста, мякоть говядины, репчатый лук, сушеный тимьян.', 'hot_03.png', '520', '250', false, false, true, false, 1, 1, 5, 50, 400),
('Драники картофельные', 3, 'Фирменные нежные драники с тонкой хрустящей корочкой.', 'картофель, лук, яйцо', 'hot_04.png', '430', '180', false, true, true, false, 1, 1, 5, 85, 456),
('Мясной гуляш', 3, 'Традиционное блюдо венгерской кухни из нежного мяса в густой подливке. ', 'свинина, лук, соус, укроп', 'hot_05.png', '450', '335', true, false, true, false, 1, 1, 5, 56, 238),
('Котлеты домашние', 3, 'Сочные и нежные котлеты. Подаются с гарниром из картофельного пюре.', 'фарш (свинина + говядина), лук, яйцо, молоко', 'hot_06.png', '440', '225', false, false, true, false, 1, 1, 5, 78, 410),
('Пельмени', 3, 'Домашние пельмени, слепленные вручную, на вкусном тесте с сочной начинкой из фарша. ', 'свинина, говядина, лук репчатый, мука', 'hot_07.png', '570', '275', false, false, true, false, 1, 1, 5, 100, 384),
('Суп из белой фасоли', 3, 'Полезный и низкокалорийный суп, который придется очень кстати для того, чтобы согреться, когда на улице заметно похолодает.', 'сельдерей, лук-порей, душица сушеная, листья розмарина, чеснок, томатная паста, помидоры, капуста, белая фасоль', 'hot_08.png', '380', '60', true, true, true, true, 1, 1, 5, 111, 410),
('Грибной суп с клецками', 3, 'Обжаренные в масле грибы своим ароматом и вкусом устроят даже отъявленного мясоеда, а аппетитнейшие творожно-сырные клецки дополняются массой полезных веществ из овощей и зелени.', 'шампиньоны, белые грибы, оливковое масло, сухое вино, тимьян', 'hot_09.png', '480', '65', false, false, true, true, 1, 1, 5, 100, 145),
('Салями', 4, 'Пицца с копченой колбасой, пепперони и сыром моцарелла.', 'колбаса салями, пепперони, сыр моцарелла, твердый сыр, томатный соус', 'pizza_01.png', '630', '65', true, false, true, false, 1, 1, 5, 53, 301),
('С курицей и ананасами', 4, 'Пицца с курицей, ананасами, помидорами и сыром моцарелла.', 'курица, твердый сыр «Моцарелла», ананасы, томатный соус', 'pizza_02.png', '500', '155', false, false, false, false, 1, 1, 5, 47, 202),
('Овощная', 4, 'Пицца из кабачков  с помидорами, цуккини, паприкой. ', 'кабачки, цуккини, твердый сыр, помидоры, базилик, ', 'pizza_03.png', '450', '115', false, false, true, false, 1, 1, 5, 15, 100),
('С шампиньонами', 4, 'Пицца с шампиньонами, сыром моцарелла, оливками, помидорами и каперсами.', 'шампиньоны, лук, чёрные оливки без косточек, сыр, каперсы', 'pizza_04.png', '550', '240', false, false, true, false, 1, 1, 5, 47, 245),
('Русская', 4, 'Пицца с картофелем, розмарином, оливками.', 'картофель, моцарелла, веточки розмарина, оливки', 'pizza_05.png', '550', '180', false, true, true, false, 1, 1, 5, 40, 145),
('Маргарита', 4, 'Пицца с томатами, моцареллой, базиликом.', 'томаты, моцарелла, базилик, масло оливковое', 'pizza_06.png', '520', '205', false, false, true, false, 1, 1, 5, 50, 195),
('Фруктовый лед', 5, 'Замороженный фруктовый десерт только из натуральных компонентов.', 'клубника, киви, сахар, вода, лимонный сок', 'dessert_01.png', '350', '55', false, true, false, true, 1, 1, 5, 100, 145),
('Торт «Дуэт»', 5, 'Черно-белый в разрезе торт с насыщенным кокосовым вкусом. ', 'печенье шоколадное, молоко, сахар, желтки яичные, мука, кокосовая стружка, сливочное масло, ванильный сахар', 'dessert_02.png', '300', '310', false, false, false, false, 1, 1, 5, 100, 145),
('Творожный рулет', 5, 'Творожно-маковый рулет. Подается с лимонный соусом.', 'лаваш, творог, яйца, сахар, мак, ванильный сахар', 'dessert_03.png', '350', '195', false, false, false, true, 1, 1, 5, 29, 145),
('Вишневый трайфл', 5, 'Нежный десерт с бисквитом, йогуртом и ягодами.', 'яйцо, сахар, мука, разрыхлитель, вишня, йогурт, миндаль', 'dessert_04.png', '380', '80', false, false, false, false, 1, 1, 5, 78, 445),
('Торт «Черный Принц»', 5, 'Фирменный десерт из нежного шоколадного бисквита и воздушного сливочно-сметанного крема.', 'яйцо, сахар, какао, кефир,сода, мука, сливочное масло, сметана, молоко', 'dessert_05.png', '390', '330', false, false, false, false, 1, 1, 5, 74, 189),
('Австрийский десерт', 5, 'Вкусный австрийский десерта под названием Кардинал. ', 'цедра апельсина, сок апельсина, мука, яичные белки, сахарная пудра, сливки, кофе', 'dessert_06.png', '330', '310', false, false, false, false, 1, 1, 5, 45, 223),
('Десерт «Крем-брюле»', 5, 'Вкусного десерта-мороженое со сливочным вкусом. ', 'молоко сгущенное вареное, сливки, орехи грецкие, шоколад, кокосовая стружка', 'dessert_07.png', '350', '170', false, false, false, true, 1, 1, 5, 78, 245),
('Оладьи из яблок', 5, 'Нежные оладушки с яблоками, которые идеально подходят на завтрак и поднимают настроение на весь день.', 'блоки,сахар, корица, мука, яйцо ', 'dessert_08.png', '280', '100', false, false, false, false, 1, 1, 5, 78, 515),
('Сырные оладьи', 5, 'Очень вкусные оладушки с сыром и колбасой. Отличный вариант для завтрака.', 'сыр твердый, колбаса докторская, лук зеленый', 'dessert_09.png', '280', '190', false, false, false, false, 1, 1, 5, 42, 321),
('Апельсиновые блины', 5, 'Великолепный апельсиновый вариант блинов с яблочной начинкой.', 'апельсин, цедра апельсина, дрожжи, сливочное масло, яблоки, корица', 'dessert_10.png', '420', '150', false, false, false, false, 1, 1, 5, 78, 410),
('Десерт «Птичье молоко»', 5, 'Нежный воздушный десерт «Птичье молоко» не только вкусный, но еще и сытный.', 'сметана, агар-агар, шоколад, кокосовая стружка', 'dessert_11.png', '420', '7', false, false, false, true, 1, 1, 5, 89, 237),
('Шоколадный мусс', 5, 'Нежный мусс с насыщенным вкусом шоколада', 'молоко, шоколад', 'dessert_12.png', '350', '85', false, false, false, true, 1, 1, 5, 124, 257),
('Кокосовое пирожное', 5, 'Нежное, мягкое и ароматное кокосовое лакомство. Начинка в меру сладкая и с насыщенным кокосовым вкусом.', 'кокосовая стружка, шоколад молочный, молоко', 'dessert_13.png', '480', '420', false, false, false, false, 1, 1, 5, 120, 521),
('Имбирный эль', 6, 'Напиток из имбиря и лимона благотворно влияет на иммунитет и заряжает энергией.', 'дрожжи, имбиря корень, лимон', 'drink_01.png', '250', '35', false, true, false, true, 1, 1, 5, 81, 327),
('Клюквенный морс', 6, 'Клюквенный морс по классическому рецепту из свежих ягод.', 'клюква, мед, лимон.', 'drink_02.png', '180', '40', false, true, false, true, 1, 1, 5, 47, 238),
('Напиток с  гранатом', 6, 'Освежающий и вкусный напиток с зернами граната и ягодами клюквы.', 'гранат, клюква, минеральная вода.', 'drink_03.png', '290', '40', false, true, true, true, 1, 1, 5, 100, 145),
('Квас', 6, 'В жаркое время года стаканчик холодного кваса отлично утолит жажду.', 'ржаной хлеб, изюм, шишки хмеля', 'drink_04.png', '180', '25', false, true, false, true, 1, 1, 5, 78, 259),
('Домашний лимончелло', 6, 'Алкогольный напиток, настоенный на лимонной кожуре.', 'лимон, водка, сахар, питьевая вода', 'drink_05.png', '250', '325', false, true, false, true, 1, 1, 5, 42, 541),
('Горячий шоколад', 6, 'Густой горячий школад с насыщенным вкусом.', 'темный шоколад (70% какао), молоко, коричневый сахар', 'drink_06.png', '280', '150', false, true, false, true, 1, 1, 5, 120, 239),
('Смородиновый кисель', 6, 'Кисель - тягучий и сладкий, немного напоминающий мармеладную конфетку по вкусу и очень ароматный, приготовленный из настоящих ягод или фруктов.', 'черная смородина,картофельный крахмал', 'drink_07.png', '280', '95', false, true, false, true, 1, 1, 5, 78, 456),
('Ванильный латте', 6, 'Нежный ванильный латте с шоколадным сиропом.', 'горький шоколад, ванильный сахар, кофе эспрессо, молоко', 'drink_08.png', '180', '50', false, true, false, true, 1, 1, 5, 54, 345),
('Лаймовый лимонад', 6, 'Этот вкусный лимонад хорош тем, что понравится и взрослым, и детям. В нём нет ни  капли алкоголя, и его с удовольствием могут пить все.', 'сок лайма, сахар, зеленый чай с жасмином, колотый лед', 'drink_09.png', '250', '15', false, true, false, true, 1, 1, 5, 78, 354),
('Лимонад с тархуном', 6, 'Домашняя версия любимого с детства лимонада «Тархун». ', 'пучок тархуна, 	лимонный сок, лимон', 'drink_10.png', '240', '26', false, true, false, true, 1, 1, 5, 78, 428);