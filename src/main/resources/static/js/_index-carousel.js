// 認養輪播取資料
$.ajax({
    type: "GET",
    async: false,
    url: "/personalAnimals?limit=6",
    success: function (data) {
        // 清空元素
        $(".animal-publish").empty();

        // 加入內容
        $.each(data["results"], function (index, item) {
            var tableContent = `
                    <div class="item img banner-img" style="background-image: url('${item.imageUrl}');">
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span id="${item.animalId}">${item.animalName}</span>
                        </div>
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span>物種: ${item.animalKind}</span>
                        </div>
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span>性別: ${item.animalSex}</span>
                        </div>
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span>地區: ${item.area}</span>
                        </div>
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span>日期: ${item.lastModifiedDate}</span>
                        </div>
                    </div>
                `;
            $(".animal-publish").append(tableContent);
        })
    }
});

// 協尋輪播取資料
$.ajax({
    type: "GET",
    async: false,
    url: "/animalsMissing?limit=6",
    success: function (data) {
        // 清空元素
        $(".animal-missing").empty();

        // 加入內容
        $.each(data["results"], function (index, item) {
            var tableContent = `
                    <div class="item img banner-img" style="background-image: url('${item.imageUrl}');">
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span id="${item.animalId}">${item.name}</span>
                        </div>
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span>物種: ${item.kind}</span>
                        </div>
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span>性別: ${item.sex}</span>
                        </div>
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span>地區: ${item.area}</span>
                        </div>
                        <div style="position: relative; top: 25%; left: 5%;">
                            <span>日期: ${item.missingDate}</span>
                        </div>
                    </div>
                `;
            $(".animal-missing").append(tableContent);
        })
    }
});

// 認養輪播設定
$(".animal-publish").owlCarousel({
    // 輪播開始位置設為隨機 數字為項目總數量
    startPosition: Math.floor(Math.random() * 6),
    // startPosition: 0,
    autoplay: true, // 自動輪播
    loop: true, // 循環輪播
    margin: 10, // 外距 10px
    nav: false, // 顯示前後項箭頭
    // navText: ["上一頁", "下一頁"], // 顯示前後項文字
    autoplayTimeout: 3000, // 切換時間
    autoplayHoverPause: true, // 滑鼠經過時暫停
    responsive: {
        0: {
            items: 1 // 螢幕大小為 0~576 顯示 1 個項目
        },
        576: {
            items: 2 // 螢幕大小為 576~992 顯示 3 個項目
        },
        992: {
            items: 3 // 螢幕大小為 992 以上 顯示 5 個項目
        }
    }
});

// 協尋輪播設定
$(".animal-missing").owlCarousel({
    // 輪播開始位置設為隨機 數字為項目總數量
    startPosition: Math.floor(Math.random() * 6),
    // startPosition: 0,
    autoplay: true, // 自動輪播
    loop: true, // 循環輪播
    margin: 10, // 外距 10px
    nav: false, // 顯示前後項箭頭
    // navText: ["上一頁", "下一頁"], // 顯示前後項文字
    autoplayTimeout: 3000, // 切換時間
    autoplayHoverPause: true, // 滑鼠經過時暫停
    responsive: {
        0: {
            items: 1 // 螢幕大小為 0~576 顯示 1 個項目
        },
        576: {
            items: 2 // 螢幕大小為 576~992 顯示 3 個項目
        },
        992: {
            items: 3 // 螢幕大小為 992 以上 顯示 5 個項目
        }
    }
});
