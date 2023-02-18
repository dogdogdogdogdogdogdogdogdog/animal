var listPage = 1;
//輸出頁碼
function pageSet(pages) {
    var pageHtml = '';
    var start, end;
    if (listPage < 6) {
        start = 1;
    } else {
        start = listPage - 5;
    }
    if (listPage > pages - 5) {
        end = pages;
    } else {
        end = listPage + 5;
    }

    if (listPage > 1) {
        pageHtml += '<span>上一頁</span>';
    }
    for (var i = start, page_cur = ''; i <= end; i++) {
        if (listPage == i) {
            page_cur = 'page_cur';
        } else {
            page_cur = '';
        }
        pageHtml += '<span class="' + page_cur + '">' + i + '</span>';
    }
    if (listPage < pages) {
        pageHtml += '<span>下一頁</span>';
    }
    $('.page_show').empty().append(pageHtml);
}

//選擇分頁
$('body').on('click', '.page_show span', function () {
    var $this = $(this);
    if ($this.hasClass('page_cur')) {
        return;
    }
    var page = $this.html();
    if (page == '上一頁') {
        listPage = listPage - 1;
        changePage(listPage);
        // goPrePage();
    } else if (page == '下一頁') {
        listPage = listPage + 1;
        changePage(listPage);
        // goNextPage();
    } else {
        listPage = parseInt(page);
        changePage(listPage);
    }
    //根據頁碼獲取當前頁列表數據
    pageSet(pages);
});