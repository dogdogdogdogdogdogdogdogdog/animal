create table personal_animal
(
    animal_id            int auto_increment
        primary key,
    animal_name          varchar(32)   null,
    animal_kind          varchar(32)   null,
    animal_variety       varchar(32)   null,
    animal_sex           varchar(32)   null,
    animal_sterilization varchar(32)   null,
    animal_bacterin      varchar(32)   null,
    image_url            varchar(256)  null,
    area                 varchar(32)   null,
    phone                varchar(10)   null,
    description          varchar(1024) null,
    created_date         timestamp     not null,
    last_modified_date   timestamp     not null
);

insert into personal_animal (animal_id, animal_name, animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, image_url, area, phone, description, created_date, last_modified_date)
values  (1, '默默', '狗', '米克斯', '公', '未絕育', '已施打', 'https://wepet.tw/storage/adopt/adopt_230131121805513212879_7094938685.jpg', '桃園市', '0987654321', '牠叫做默默，個性活潑天然呆愛撒嬌喜歡看窗外，會定點上廁所。
右後腳腿骨因受傷有打鋼釘，已完全康復健步如飛。

因社區禁養會出聲的寵物及無法長時間陪伴身邊，與管委會協商後只能送養...
請給默默一個家，謝謝。', '2021-01-31 16:42:32', '2021-03-31 16:42:34'),
        (2, '牛奶', '狗', '米克斯', '母', '未絕育', '未施打', 'https://wepet.tw/storage/adopt/adopt_230130160243722512876_6878365763.jpg', '南投縣', '0963214789', '牛奶是一隻個性溫馴、聽話體貼並且善解人意的寶貝，就算之前經歷過不當飼養和被遺棄，牛奶依然不放棄相信人類，希望這樣的牛奶也能擁有幸福的機會。', '2023-01-18 17:17:29', '2023-01-18 17:17:31'),
        (3, '欸', '貓', '米克斯', '母', '已絕育', '未施打', 'https://wepet.tw/storage/adopt/adopt_230130095508667912875_6501743708.jpg', '新竹縣', '0988521456', '一歲多的乖巧女生，不會抓人咬人，有點膽小但熟了會撒嬌。本人因為對貓毛過敏，影響生活，故想找人繼續照顧她。', '2023-01-27 17:18:47', '2023-01-29 17:18:50'),
        (4, '憨憨', '貓', '米克斯', '公', '已絕育', '已施打', 'https://wepet.tw/storage/adopt/adopt_230127215749686812864_1338627869.jpg', '新北市', '0963258741', '可愛憨貓尋找有愛的家人
公貓已結紮已打疫苗除蚤
【地點】:新北汐止
【顏色】:虎斑
【貓咪年紀】:大約2歲
【個性】:比較膽小怕生新環境大概7~10天出來走動
熟悉後陪睡給抱
【領養人條件】:愛貓人士', '2021-01-31 17:20:19', '2022-11-24 17:20:25');
