function activeMenu(){
    let path = window.location.pathname;
    console.log(path)
    const menuItem = document.querySelectorAll("#main-menu .navbar-nav .nav-link");
    menuItem.forEach(function (menu){
        if (menu.getAttribute("href") === path){
            menu.classList.add("active");
        }
    })
}
activeMenu();