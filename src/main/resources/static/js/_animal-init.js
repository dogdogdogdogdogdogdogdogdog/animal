// js 改動建議清除瀏覽器快取 否則瀏覽器可能會從 VM 中執行改動前的暫存檔案

// 宣告全域變數 將登入資訊存入變數中 後續可善加使用於其他頁面
// httpStatus 於頁面中用於取得目前的登入狀態碼 (200: 已登入,  302:未登入)
// user 於頁面中用於取得目前登入者資訊
var httpStatus;
var user;

// 取得登入 session
$.ajax({
    type: "GET",
    url: "/session-userId",
    // 取消非同步執行以確保 ajax 完成前停止頁面其他內容的處理
    async: false,
    // success 結果: 經攔截器判斷攜帶 session 回傳 true
    success: function (data, textStatus, xhr) {
        httpStatus = xhr.status;
        user = data;
        console.log("[攔截器登入判斷: " + textStatus + "]");
        console.log("[HTTP狀態碼: " + xhr.status + "]");
        console.log("[已登入]");
        // 登入者 JSON 資訊
        console.log(user);

        // 讀取頁腳
        $("#footer-page").load("/footer");
        // 讀取頁首後執行
        $("#header-page").load("/header", function () {
            //取得 title 名稱放入迷你導覽列
            $("#page-title").text($("title")[0].innerText);
            // 導覽列會員登入顯示項目
            navbarVisibility();
        });
    },
    // error 結果: 經攔截器判斷未攜帶登入 session 回傳 false , 且攔截器將 HTTP 狀態碼設置為 302
    error: function (xhr, textStatus) {
        httpStatus = xhr.status;
        user = null;
        console.log("[攔截器登入判斷: " + textStatus + "]");
        console.log("[HTTP狀態碼: " + xhr.status + "]");
        console.log("[未登入]");

        // 讀取頁腳
        $("#footer-page").load("/footer");
        // 讀取頁首後執行
        $("#header-page").load("/header", function () {
            //取得 title 名稱放入迷你導覽列
            $("#page-title").text($("title")[0].innerText);
        });

        // 未登入狀態訪問會員功能頁面時跳轉回首頁
        var url = location.pathname.replaceAll("/", "");
        if (url == "user_publish_history" || url == "user_management" || url == "publish") {
            window.location.replace("/index");
        }
    }
});

// 導覽列會員登入顯示項目
function navbarVisibility() {
    // 登入後顯示 [userMenuLogin]，未登入顯示 [userMenuSignOut]
    document.getElementById("userMenuLogin").removeAttribute("style");
    document.getElementById("userMenuSignOut").style.display = "none";
    // // 登入後顯示 {會員Email} 登出
    // document.getElementById("login_stat").style.visibility = "visible";
    // document.getElementById("login_stat").innerHTML = `${user.email} 登出`;
    // // 登入後導覽列下拉選單隱藏「登入」連結
    // document.getElementById("login").style.display = "none";
    // // 登入後導覽列下拉選單顯示「登出」連結
    // document.getElementById("sign_out").removeAttribute("style");
    // // 登入後導覽列下拉選單顯示「會員資料變更」連結
    // document.getElementById("userManagement").removeAttribute("style");
    // // 登入後導覽列下拉選單顯示「會員刊登送養」連結
    // document.getElementById("publish").removeAttribute("style");
    // // 登入後導覽列下拉選單顯示「送養資訊管理」連結
    // document.getElementById("userPublishHistory").removeAttribute("style");
}
