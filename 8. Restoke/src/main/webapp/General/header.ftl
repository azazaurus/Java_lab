<#ftl encoding="UTF-8"/>
<html lang="ru">
<#macro title>Nav_bar</#macro>
<#macro content>
<nav class="navbar">

    <div class="btn">
        <span class="fas fa-bars"></span>
    </div>

    <div class="logo">
        <a href="/restoke_war/main"><h4>Restoke.</h4></a>
    </div>
    <ul class="nav-links">

        <li class="icon_list">
            <form method="get" action="/restoke_war/searching_page">
                <div class="search-box">
                    <input type="text" class="search-txt" placeholder="Search" name="search">
                    <a href="#" class="search-btn"><i class="fa fa-search" aria-hidden="true"></i></a>
                </div>
            </form>
        </li>

        <li ${hidden} class="icon_list">
            <a class="icon" href="/restoke_war/my_account">
                <svg width="1.7em" height="1.8em" viewBox="0 0 16 16" class="bi bi-person" fill="white"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M10 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                </svg>
            </a>
        </li>

        <li ${hidden} class="icon_list">
            <a class="icon" href="/restoke_war/favorites">
                <svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-bookmark-heart" fill="white"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
                    <path fill-rule="evenodd"
                          d="M8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
                </svg>
            </a>
        </li>

        <li class="icon_list">
            <a class="icon" href="/restoke_war/create_newAd">
                <svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-bag-plus" fill="white"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M8 1a2.5 2.5 0 0 0-2.5 2.5V4h5v-.5A2.5 2.5 0 0 0 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5v9a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V5H2z"/>
                    <path fill-rule="evenodd"
                          d="M8 7.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V12a.5.5 0 0 1-1 0v-1.5H6a.5.5 0 0 1 0-1h1.5V8a.5.5 0 0 1 .5-.5z"/>
                </svg>
            </a>
        </li>

        <li ${hidden} class="icon_list">
            <a class="icon" href="/restoke_war/my_messages">
                <svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-chat-dots" fill="white"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
                    <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                </svg>
            </a>
        </li>

        <li class="icon_list">
            <a class="icon" href="/restoke_war/login">Log&nbsp;in</a>
        </li>
    </ul>
</nav>
</#macro>
</html>