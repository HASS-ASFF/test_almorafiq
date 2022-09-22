function ShowMenu(){
    var topNav = document.getElementById('topnav');
    var bars = document.getElementById('iconchange');
    if(topNav.className === "navbar"){
        topNav.className = " show";
        bars.className = "fa fa-close";
    }
    else{
        topNav.className = "navbar";
        bars.className = "fa fa-bars";
    }
}