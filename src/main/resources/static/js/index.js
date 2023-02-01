//手動換頁
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
    showDivs(slideIndex += n);
}

function currentDiv(n) {
    showDivs(slideIndex = n);
}

function showDivs(n) {
    var i;
    var x = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("demo");
    if (n > x.length) {slideIndex = 1}
    if (n < 1) {slideIndex = x.length}
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" w3-white", "");
    }
    x[slideIndex-1].style.display = "block";
    dots[slideIndex-1].className += " w3-white";
}

//自動輪播
var slideIndex = 0;
showSlides();

function showSlides(){
    var i;
    var sildes = document.getElementsByClassName("mySlides");
    for(i = 0; i<sildes.length; i++){
        sildes[i].style.display = "none";
    }
    slideIndex++;
    if(slideIndex > sildes.length){
        slideIndex = 1
    }
    sildes[slideIndex -1].style.display = "block";
    setTimeout(showSlides,5000);
}