// Your web app's Firebase configuration
let firebaseConfig = {
    apiKey: "AIzaSyB2JvuzjJGXwhGchDFXUBZFlZoK_PmsP30",
    authDomain: "userauthentication01-4554b.firebaseapp.com",
    databaseURL: "https://userauthentication01-4554b.firebaseio.com",
    projectId: "userauthentication01-4554b",
    storageBucket: "",
    messagingSenderId: "82013646771",
    appId: "1:82013646771:web:e63064aaede551c7"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

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
            let token = result.credential.accessToken;
            // The signed-in user info.
            let user = result.user;

            let displayName = user.displayName;
            let email = user.email;
            let emailVerified = user.emailVerified;
            let photoURL = user.photoURL;
            let isAnonymous = user.isAnonymous;
            let uid = user.uid;
            let providerData = user.providerData;

            // [START_EXCLUDE]
            document.getElementById('quickstart-oauthtoken').textContent = token;

            let registerUrl = '/loginApi/register/';
            let requestBody = JSON.stringify({token: token, uid: uid});
            let requestParam = {
                method: "post",
                body: requestBody,
                headers: new Headers({"Content-type": "application/json"})
            };
            // TODO tokenをajaxでサーバー側に送信する。
            let req = new Request(registerUrl, requestParam);
            fetch(req).then(response => {
                console.log(response.status);
                return response.json();
            }).then(json => {
                console.log('json:', json);
                document.getElementById('sign-in-status').innerHTML = json.result;
            }).catch(err => {
                console.log(err)
            });

            // [END_EXCLUDE]
        }).catch(function (error) {
            // Handle Errors here.
            let errorCode = error.code;
            let errorMessage = error.message;
            // The email of the user's account used.
            let email = error.email;
            // The firebase.auth.AuthCredential type that was used.
            let credential = error.credential;
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
    document.getElementById('sign-in').disabled = true;
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
            let displayName = user.displayName;
            let email = user.email;
            let emailVerified = user.emailVerified;
            let photoURL = user.photoURL;
            let isAnonymous = user.isAnonymous;
            let uid = user.uid;
            let providerData = user.providerData;
            // [START_EXCLUDE]
            document.getElementById('sign-in-status').textContent = 'サインイン';
            document.getElementById('sign-in').textContent = 'サインアウト';
            document.getElementById('quickstart-account-details').textContent = JSON.stringify(user, null, '  ');
            // [END_EXCLUDE]
        } else {
            // User is signed out.
            // [START_EXCLUDE]
            document.getElementById('sign-in-status').textContent = 'Signed out';
            document.getElementById('sign-in').textContent = 'Sign in with Google';
            document.getElementById('quickstart-account-details').textContent = 'null';
            document.getElementById('quickstart-oauthtoken').textContent = 'null';
            // [END_EXCLUDE]
        }
        // [START_EXCLUDE]
        // $('#sign-in').attr('disabled', 'false');
        document.getElementById('sign-in').disabled = false;
        // [END_EXCLUDE]
    });
    // [END authstatelistener]

    // event listener
    document.getElementById('sign-in').addEventListener('click', toggleSignIn, false);
}

window.onload = function () {
    initApp();
};
