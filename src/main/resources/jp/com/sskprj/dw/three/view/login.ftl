<!DOCTYPE html>
<#-- @ftlvariable name="" typle="jp.com.sskprj.dw.three.view.LoginView" -->
<html lang="ja">
<head>
    <meta charset=utf-8/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${viewHeaderData.title}</title>

    <!-- Material Design Theming -->
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.orange-indigo.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script defer src="https://code.getmdl.io/1.1.3/material.min.js"></script>

    <link rel="stylesheet" href="/assets/css/loginMain.css">

    <!-- Import and configure the Firebase SDK -->
    <!-- These scripts are made available when the app is served or deployed on Firebase Hosting -->
    <!-- If you do not serve/host your project using Firebase Hosting see https://firebase.google.com/docs/web/setup -->
    <script src="https://www.gstatic.com/firebasejs/5.10.1/firebase-app.js"></script>

    <!-- Add Firebase products that you want to use -->
    <script src="https://www.gstatic.com/firebasejs/5.10.1/firebase-auth.js"></script>
    <script src="/assets/js/init-firebase.js"></script>

    <script type="text/javascript">
        /**
         * Function called when clicking the Login/Logout button.
         */
        // [START buttoncallback]
        function toggleSignIn() {
            if (!firebase.auth().currentUser) {
                // [START createprovider]
                var provider = new firebase.auth.GoogleAuthProvider();
                // [END createprovider]
                // [START addscopes]
                provider.addScope('https://www.googleapis.com/auth/contacts.readonly');
                // [END addscopes]
                // [START signin]
                firebase.auth().signInWithPopup(provider).then(function (result) {
                    // This gives you a Google Access Token. You can use it to access the Google API.
                    var token = result.credential.accessToken;
                    // The signed-in user info.
                    var user = result.user;
                    // [START_EXCLUDE]
                    document.getElementById('quickstart-oauthtoken').textContent = token;
                    // [END_EXCLUDE]
                }).catch(function (error) {
                    // Handle Errors here.
                    var errorCode = error.code;
                    var errorMessage = error.message;
                    // The email of the user's account used.
                    var email = error.email;
                    // The firebase.auth.AuthCredential type that was used.
                    var credential = error.credential;
                    // [START_EXCLUDE]
                    if (errorCode === 'auth/account-exists-with-different-credential') {
                        alert('You have already signed up with a different auth provider for that email.');
                        // If you are using multiple auth providers on your app you should handle linking
                        // the user's accounts here.
                    } else {
                        document.getElementById('jsErrorSpan').innerText = errorMessage;
                        console.error(error);
                    }
                    // [END_EXCLUDE]
                });
                // [END signin]
            } else {
                // [START signout]
                firebase.auth().signOut();
                // [END signout]
            }
            // [START_EXCLUDE]
            document.getElementById('quickstart-sign-in').disabled = true;
            // [END_EXCLUDE]
        }

        // [END buttoncallback]
        /**
         * initApp handles setting up UI event listeners and registering Firebase auth listeners:
         *  - firebase.auth().onAuthStateChanged: This listener is called when the user is signed in or
         *    out, and that is where we update the UI.
         */
        function initApp() {
            // Listening for auth state changes.
            // [START authstatelistener]
            firebase.auth().onAuthStateChanged(function (user) {
                if (user) {
                    // User is signed in.
                    var displayName = user.displayName;
                    var email = user.email;
                    var emailVerified = user.emailVerified;
                    var photoURL = user.photoURL;
                    var isAnonymous = user.isAnonymous;
                    var uid = user.uid;
                    var providerData = user.providerData;
                    // [START_EXCLUDE]
                    document.getElementById('quickstart-sign-in-status').textContent = 'サインイン';
                    document.getElementById('quickstart-sign-in').textContent = 'サインアウト';
                    document.getElementById('quickstart-account-details').textContent = JSON.stringify(user, null, '  ');
                    // [END_EXCLUDE]
                } else {
                    // User is signed out.
                    // [START_EXCLUDE]
                    document.getElementById('quickstart-sign-in-status').textContent = 'Signed out';
                    document.getElementById('quickstart-sign-in').textContent = 'Sign in with Google';
                    document.getElementById('quickstart-account-details').textContent = 'null';
                    document.getElementById('quickstart-oauthtoken').textContent = 'null';
                    // [END_EXCLUDE]
                }
                // [START_EXCLUDE]
                document.getElementById('quickstart-sign-in').disabled = false;
                // [END_EXCLUDE]
            });
            // [END authstatelistener]
            document.getElementById('quickstart-sign-in').addEventListener('click', toggleSignIn, false);
        }

        window.onload = function () {
            initApp();
        };
    </script>
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
                    <button disabled class="mdl-button mdl-js-button mdl-button--raised" id="quickstart-sign-in">Sign in
                        with Google
                    </button>

                    <!-- Container where we'll display the user details -->
                    <div class="quickstart-user-details-container">
                        Firebase sign-in status: <span id="quickstart-sign-in-status">Unknown</span>
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