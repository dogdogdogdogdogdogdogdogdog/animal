
    let xhr = new XMLHttpRequest()
    let curPage=1
    xhr.addEventListener("load", function () {
        if (xhr.status == 200) {
            processPage(curPage,xhr)
        }
    })
    xhr.open('get', `/missingAnimals?offset=${((curPage - 1) * 2)}`)
    xhr.send()


    function  processPage(curPage,xhr) {

                let pageContent = JSON.parse(xhr.responseText);
                console.log(pageContent.missingAnimals)
                let total = pageContent.total
                let limit = pageContent.limit
                if (total <= 0) {
                    return
                }
                if ((total % limit) == 0) {
                    let pages = total / limit
                } else {
                    let pageShow = document.getElementById('ps')
                    let pages = Math.ceil(total / limit)

                    const preText = document.createTextNode('上一頁')
                    let preBtn = document.createElement('span')
                    preBtn.appendChild(preText)
                    preBtn.onclick=function (){
                        let xhr = new XMLHttpRequest()
                        xhr.addEventListener("load", function () {
                            if (xhr.status == 200) {
                                let curPage= document.getElementById("pageInfo").className
                                curPage--
                                document.getElementById("pageInfo").className=curPage
                                document.getElementById("ps").innerHTML=''
                                processPage(curPage,xhr)
                            }
                        })
                        xhr.open('get', `/missingAnimals?offset=${((curPage - 1) * 2)}`)
                        xhr.send()
                    }

                    const nextText = document.createTextNode('下一頁')
                    let nextBtn = document.createElement('span')
                    nextBtn.appendChild(nextText)
                    nextBtn.onclick=function (){
                        let xhr = new XMLHttpRequest()
                        xhr.addEventListener("load", function () {
                            if (xhr.status == 200) {
                                let curPage= document.getElementById("pageInfo").className
                                curPage++
                                document.getElementById("pageInfo").className=curPage
                                document.getElementById("ps").innerHTML=''
                                processPage(curPage,xhr)
                            }
                        })
                        xhr.open('get', `/missingAnimals?offset=${((curPage - 1) * 2)}`)
                        xhr.send()
                    }

                    const curPageText = document.createTextNode(curPage)
                    let curPageBtn = document.createElement('span')
                    curPageBtn.setAttribute('class', 'page_cur')
                    curPageBtn.appendChild(curPageText)

                    if (pages > 1) {
                        if (curPage > 1) {
                            if (curPage < pages) {
                                pageShow.appendChild(preBtn)
                                generateBtn(0, curPage, pageShow)
                                pageShow.appendChild(curPageBtn)
                                generateBtn(curPage, pages + 1, pageShow)
                                pageShow.appendChild(nextBtn)
                                console.log('//顯示上一頁數字方塊紐')
                                console.log('//顯示下一頁數字方塊紐')
                            } else {
                                //最後一頁
                                pageShow.appendChild(preBtn)
                                generateBtn(0, pages, pageShow)
                                pageShow.appendChild(curPageBtn)
                                console.log('//顯示上一頁數字方塊紐')
                            }
                        } else {
                            //第一頁
                            pageShow.appendChild(curPageBtn)
                            generateBtn(1, pages + 1, pageShow)
                            pageShow.appendChild(nextBtn)
                            console.log('//顯示下一頁數字方塊紐')

                        }
                    } else {
                        pageShow.appendChild(curPageBtn)

                    }
                }

            }

function generateBtn(min,max,element){
    for(let i=min+1;i<max;i++){
        const btnNumber=document.createTextNode(i)
        let pageBtn=document.createElement('span')
        pageBtn.appendChild(btnNumber)
        pageBtn.onclick=function (){

            let xhr = new XMLHttpRequest()
            xhr.addEventListener("load", function () {
                if (xhr.status == 200) {
                    document.getElementById("ps").innerHTML=''
                    processPage(i,xhr)
                    document.getElementById("pageInfo").className=i
                }
            })
            xhr.open('get', `/missingAnimals?offset=${((i - 1) * 2)}`)
            xhr.send()
        }
        element.appendChild(pageBtn)
    }
}

// function  sendParamsToServer(curPage,countOfPerPage){
//     let xhr = new XMLHttpRequest()
//     xhr.addEventListener("load", function () {
//         if (xhr.status == 200) {
//             document.getElementById("ps").innerHTML=''
//             processPage(curPage,xhr)
//             document.getElementById("pageInfo").className=curPage
//         }
//     })
//     xhr.open('get', `http://localhost:8080/missingAnimals?offset=${((curPage - 1) * countOfPerPage)}`)
//     xhr.send()
// }


