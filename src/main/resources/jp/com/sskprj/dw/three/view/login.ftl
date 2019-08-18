<!DOCTYPE html>
<#-- @ftlvariable name="" type="jp.com.sskprj.dw.three.view.LoginView" -->
<html lang="ja">
<head>
    <meta charset=utf-8 >
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${viewHeaderData.title}</title>

    <!-- Material Design Theming -->
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.orange-indigo.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script defer src="https://code.getmdl.io/1.1.3/material.min.js"></script>
    <script src="/assets/js/lib/jquery-3.4.1.js"></script>

    <link rel="stylesheet" href="/assets/css/loginMain.css">

    <!-- Import and configure the Firebase SDK -->
    <!-- These scripts are made available when the app is served or deployed on Firebase Hosting -->
    <!-- If you do not serve/host your project using Firebase Hosting see https://firebase.google.com/docs/web/setup -->
    <script src="https://www.gstatic.com/firebasejs/5.10.1/firebase-app.js"></script>

    <!-- Add Firebase products that you want to use -->
    <script src="https://www.gstatic.com/firebasejs/5.10.1/firebase-auth.js"></script>
    <script src="/assets/js/init-firebase.js"></script>

</head>
<body>


<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-header">

    <!-- Header section containing title -->
    <header class="mdl-layout__header mdl-color-text--white mdl-color--light-blue-700">
        <div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-grid">
            <div class="mdl-layout__header-row mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--8-col-desktop">
                <a href="/login/"><h3>Firebaseでの認証</h3></a>
            </div>
        </div>
    </header>

    <main class="mdl-layout__content mdl-color--grey-100">
        <div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-grid">
            <span id="jsErrorSpan"></span>
            <!-- Container for the demo -->
            <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-desktop">
                <div class="mdl-card__title mdl-color--light-blue-600 mdl-color-text--white">
                    <h2 class="mdl-card__title-text">Google Authentication with Popup</h2>
                </div>
                <div class="mdl-card__supporting-text mdl-color-text--grey-600">
                    <p>Sign in with your Google account below.</p>

                    <!-- Button that handles sign-in/sign-out -->
                    <button disabled class="mdl-button mdl-js-button mdl-button--raised" id="sign-in">Googleでサインイン
                    </button>

                    <!-- Container where we'll display the user details -->
                    <div class="quickstart-user-details-container">
                        Firebase sign-in status: <span id="sign-in-status">不明</span>
                        <div>Firebase auth <code>currentUser</code> object value:</div>
                        <pre><code id="quickstart-account-details">null</code></pre>
                        <div>Google OAuth Access Token:</div>
                        <pre><code id="quickstart-oauthtoken">null</code></pre>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>


<!-- TODO: Add SDKs for Firebase products that you want to use
     https://firebase.google.com/docs/web/setup#config-web-app -->

</body>
</html>
